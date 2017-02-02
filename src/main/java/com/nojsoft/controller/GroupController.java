package com.nojsoft.controller;

import com.nojsoft.constants.GeneralConstants;
import com.nojsoft.dao.GroupDao;
import com.nojsoft.dao.UserDao;
import com.nojsoft.model.Group;
import com.nojsoft.model.GroupParticipant;
import com.nojsoft.model.GroupSearch;
import com.nojsoft.model.User;
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
    private GroupDao groupDao;
    @Autowired
    private UserDao userDao;

    @PostMapping("/group/save")
    public Group save(@RequestBody Group group) {
        return groupDao.saveOrUpdate(group);
    }

    @GetMapping("/group/owner/{ownerId}")
    public List<Group> getGroupsByOwnerId(@PathVariable long ownerId) {
        return groupDao.getGroupsByOwner(ownerId);
    }

    @PostMapping("/group/requestaccess")
    public ResponseEntity<String> requestAccess(@RequestBody GroupParticipant groupParticipant) {
        groupDao.requestAccess(groupParticipant);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/group/allowaccess")
    public ResponseEntity<String> allowAccess(@RequestBody GroupParticipant groupParticipant) {
        groupDao.allowAccess(groupParticipant);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/group/denyaccess")
    public ResponseEntity<String> denyAccess(@RequestBody GroupParticipant groupParticipant) {
        groupDao.denyAccess(groupParticipant);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/group/search")
    public List<Group> searchGroup(@RequestBody GroupSearch groupSearch) {
        if (groupSearch.getFilter().equals(GeneralConstants.EMAIL)) {
            List<User> users = userDao.getUsersByAccessKey(groupSearch.getValue());
            return users.isEmpty() ? null : groupDao.getGroupsByOwner(users.get(0).getId());
        }
        return null;
    }
}