package com.nojsoft.controller;

import com.nojsoft.dao.UserDao;
import com.nojsoft.model.User;
import com.nojsoft.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alan on 2/6/17.
 */
@RestController
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public User save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @PostMapping("/user/login")
    public User login(@RequestBody User user) {
        return userService.getUserStatus(user);
    }
}
