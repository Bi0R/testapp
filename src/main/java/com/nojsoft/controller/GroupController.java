package com.nojsoft.controller;

import com.nojsoft.constants.GeneralConstants;
import com.nojsoft.dao.GroupDao;
import com.nojsoft.dao.UserDao;
import com.nojsoft.model.Group;
import com.nojsoft.model.GroupParticipant;
import com.nojsoft.model.GroupSearch;
import com.nojsoft.model.User;
import com.nojsoft.services.GroupService;
import com.nojsoft.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alan on 1/14/17.
 */
@RestController
@Transactional
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @PostMapping("/group/save")
    public Group save(@RequestBody Group group) {
        return groupService.saveOrUpdate(group);
    }

    @GetMapping("/group/owner/{ownerId}")
    public List<Group> getGroupsByOwnerId(@PathVariable long ownerId) {
        return groupService.getGroupsByOwner(ownerId);
    }

    @PostMapping("/group/requestaccess")
    public ResponseEntity<String> requestAccess(@RequestBody GroupParticipant groupParticipant) {
        groupService.requestAccess(groupParticipant);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/group/allowaccess")
    public ResponseEntity<String> allowAccess(@RequestBody GroupParticipant groupParticipant) {
        groupService.allowAccess(groupParticipant);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/group/denyaccess")
    public ResponseEntity<String> denyAccess(@RequestBody GroupParticipant groupParticipant) {
        groupService.denyAccess(groupParticipant);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/group/search")
    public List<Group> searchGroup(@RequestBody GroupSearch groupSearch) {
        if (groupSearch.getFilter().equals(GeneralConstants.EMAIL)) {
            List<User> owners = userService.getUsersByAccessKey(groupSearch.getValue());
            return owners.isEmpty() ? null : groupService.getGroupsByOwnerStatus(owners.get(0).getId(),
                    groupSearch.getUserId());
        }
        return null;
    }

    @GetMapping("/group/participant/{participantId}")
    public List<Group> getGroupsByParticipantId(@PathVariable long participantId) {
        return groupService.getGroupsByParticipant(participantId);
    }

    @PostMapping("/group/detail")
    public Group groupDetail(@RequestBody Group group) {
        return groupService.getFullGroup(group);
    }
}