package com.nojsoft.controller;

import com.nojsoft.model.Group;
import com.nojsoft.model.User;
import com.nojsoft.services.GroupService;
import com.nojsoft.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alan on 2/6/17.
 */
@RestController
@Transactional
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/v1/users")
    public User save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/v1/users/{userId}")
    public User login(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/v1/users/{userId}/groups/created")
    public List<Group> getGroupsByOwnerId(@PathVariable long userId) {
        return userService.getGroupsByOwner(userId);
    }

    @GetMapping("/v1/users/{participantId}/groups/accepted")
    public List<Group> getGroupsByParticipantId(@PathVariable long participantId) {
        return userService.getGroupsByParticipant(participantId);
    }
}
