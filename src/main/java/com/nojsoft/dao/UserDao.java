package com.nojsoft.dao;

import com.nojsoft.constants.DataBaseConstants;
import com.nojsoft.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jorge on 22/01/17.
 */


@Repository("UserDao")
public class UserDao extends GeneralDao {

    public User saveOrUpdate(User user) {
        return super.saveOrUpdateEntity(user);
    }

    public List<User> getUsersByAccessKey(String accessKey) {
        return super.findByField(User.class, DataBaseConstants.USER_ACCESS_KEY_FIELD, accessKey);
    }

    public User getUserStatus(User user) {
        List<User> users = super.findByField(User.class, DataBaseConstants.USER_TOKEN_FIELD, user.getToken());
        user.setActive(!(users.isEmpty() || users.size() == 0));
        return user;
    }

}
