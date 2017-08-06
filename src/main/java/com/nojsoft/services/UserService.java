package com.nojsoft.services;

import com.nojsoft.dao.GroupDao;
import com.nojsoft.dao.UserDao;
import com.nojsoft.model.Group;
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

    @Autowired
    GroupDao groupDao;

    public User saveOrUpdate(User user) {
        return userDao.saveOrUpdate(user);
    }

    public List<User> getUsersByAccessKey(String accessKey) {
        return userDao.getUsersByAccessKey(accessKey);
    }

    public User getUser(long userId) {
        return userDao.getUser(userId);
    }

    public List<Group> getGroupsByOwner(long userId) { return groupDao.getGroupsByOwner(userId); }

    public List<Group> getGroupsByParticipant(long participantId) {
        return groupDao.getGroupsByParticipant(participantId);
    }

}
