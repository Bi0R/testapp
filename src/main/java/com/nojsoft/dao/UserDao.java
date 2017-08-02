package com.nojsoft.dao;

import com.nojsoft.constants.DataBaseConstants;
import com.nojsoft.model.Group;
import com.nojsoft.model.User;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by jorge on 22/01/17.
 */


@Repository("UserDao")
public class UserDao extends GeneralDao {

    private final String QUERY_USERS_GROUP = "SELECT u.* FROM users u " +
            "JOIN group_participants gp " +
            "ON u.id = gp.user_id " +
            "WHERE group_id = :id " +
            "AND gp.status = {0}";

    public User saveOrUpdate(User user) {
        return super.saveOrUpdateEntity(user);
    }

    public List<User> getUsersByAccessKey(String accessKey) {
        return super.findByField(User.class, DataBaseConstants.USER_EMAIL_FIELD, accessKey);
    }

    public User getUserStatus(String userId) {
        User user = null;
        System.out.println("Method: "+userId);
        List<User> users = super.findByField(User.class, DataBaseConstants.USER_UID_FIELD, userId);
        if(!users.isEmpty()){
            user=users.get(0);
        }
        System.out.println("Paso"+users.isEmpty());
        //suser.setActive(!(users.isEmpty() || users.size() == 0));
        return user;
    }


    public List<User> getRequestersGroup(Group group) {
        List<User> users = getParticipantsByStatus(group, DataBaseConstants.USER_REQUESTER);
        for (User user : users) {
            user.setEmail(null);
            user.setActive(true);
            user.setUid(null);
            user.setAuthenticationType(null);
        }
        return users;
    }

    public List<User> getParticipantsGroup(Group group) {
        List<User> users = getParticipantsByStatus(group, DataBaseConstants.USER_ACCEPTED);
        for (User user : users) {
            user.setEmail(null);
            user.setActive(true);
            user.setUid(null);
            user.setAuthenticationType(null);
        }
        return users;
    }

    private List<User> getParticipantsByStatus(Group group, int status) {
        String query = MessageFormat.format(QUERY_USERS_GROUP, status);
        SQLQuery sqlQuery = super.getSession().createNativeQuery(query);
        sqlQuery.addEntity(User.class);
        sqlQuery.setParameter(DataBaseConstants.ID_FIELD, group.getId());
        return sqlQuery.list();
    }

}
