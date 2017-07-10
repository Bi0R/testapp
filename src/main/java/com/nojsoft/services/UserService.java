package com.nojsoft.services;

import com.nojsoft.dao.UserDao;
import com.nojsoft.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jorge on 01/07/17.
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User saveOrUpdate(User user) {
        return userDao.saveOrUpdate(user);
    }

    public List<User> getUsersByAccessKey(String accessKey) {
        return userDao.getUsersByAccessKey(accessKey);
    }

    public User getUserStatus(User user) {
        return userDao.getUserStatus(user);
    }
}
