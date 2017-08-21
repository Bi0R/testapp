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

import javax.websocket.server.PathParam;
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

    @PostMapping("/v1/users/{userId}/groups")
    public Group save(@PathVariable Long userId, @RequestBody Group group) {
        group.setOwnerId(userId);
        return groupService.saveOrUpdate(group);
    }

    @PostMapping("/v1/users/{userId}/groups/{groupId}/request_access")
    public ResponseEntity<String> requestAccess(@PathVariable long userId, @PathVariable long groupId) {
        GroupParticipant groupParticipant = new GroupParticipant();
        groupParticipant.setUserId(userId);
        groupParticipant.setGroupId(groupId);
        groupService.requestAccess(groupParticipant);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PostMapping("/v1/users/{userId}/groups/{groupId}/allow_access")
    public ResponseEntity<String> allowAccess(@PathVariable long groupId, @RequestBody long participantId) {
        GroupParticipant groupParticipant = new GroupParticipant();
        groupParticipant.setUserId(participantId);
        groupParticipant.setGroupId(groupId);
        groupService.allowAccess(groupParticipant);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PostMapping("/v1/users/{userId}/groups/{groupId}/deny_access")
    public ResponseEntity<String> denyAccess(@PathVariable long groupId, @RequestBody long participantId) {
        GroupParticipant groupParticipant = new GroupParticipant();
        groupParticipant.setUserId(participantId);
        groupParticipant.setGroupId(groupId);
        groupService.denyAccess(groupParticipant);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PostMapping("/group/search")
    public List<Group> searchGroup(@RequestBody GroupSearch groupSearch) {
        return groupService.searchGroup(groupSearch);
    }

    @PostMapping("/group/detail")
    public Group groupDetail(@RequestBody Group group) {
        return groupService.getFullGroup(group);
    }

}