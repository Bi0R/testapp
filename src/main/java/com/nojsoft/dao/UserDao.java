package com.nojsoft.dao;

import com.nojsoft.constants.DataBaseConstants;
import com.nojsoft.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jorge on 22/01/17.
 */
@Repository
public class UserDao extends GeneralDao{
    public List<User> getUsersByAccessKey (String accessKey){
        return super.findByField(DataBaseConstants.USER_ACCESS_KEY_FIELD, accessKey);
    }

}
