package com.nojsoft.dao;

import com.nojsoft.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jorge on 22/01/17.
 */
@Repository("UserDao")
public class UserDao extends GeneralDao {
    public List<User> getUsersByAccessKey(String accessKey) {
        return super.findByField(User.class, "accessKey", accessKey);
    }

}
