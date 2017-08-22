package com.nojsoft.dao;

import com.nojsoft.constants.DataBaseConstants;
import com.nojsoft.model.Group;
import com.nojsoft.model.GroupParticipant;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan on 1/14/17.
 */

@Repository("GroupDao")
public class GroupDao extends GeneralDao {

    @Autowired
    UserDao userDao;

    public static String GROUP_PARTICIPANT_QUERY = "SELECT g.* from groups g " +
            "JOIN group_participants gp ON g.id = gp.group_id WHERE gp.status = 1 AND gp.user_id =:userId";

    public static String GROUP_OWNER_SEARCH = "SELECT g.* FROM groups g" +
            " JOIN group_participants gp ON g.id = gp.group_id AND gp.status NOT IN (0,1) AND gp.user_id = :userId" +
            " WHERE  g.owner_id = :ownerId AND g.owner_id <> :userId" +
            " UNION ALL" +
            " SELECT g.* FROM groups g" +
            " WHERE  g.owner_id = :ownerId AND g.owner_id <> :userId" +
            " AND NOT EXISTS" +
            " (SELECT gp.group_id FROM group_participants gp" +
            " WHERE gp.user_id = :userId AND gp.group_id=g.id);";


    public Group saveOrUpdate(Group group) {
        return super.saveOrUpdateEntity(group);
    }

    public void delete(Group group) {
        super.deleteEntity(group);
    }

    public List<Group> getGroupsByOwner(long ownerId) {
        return super.findByField(Group.class, DataBaseConstants.OWNER_ID_FIELD, ownerId);
    }

    public List<Group> getGroupsByOwnerStatus(long ownerId, long userId) {
        SQLQuery query = super.getSession().createNativeQuery(GROUP_OWNER_SEARCH);
        query.addEntity(Group.class);
        query.setParameter(DataBaseConstants.OWNER_ID_FIELD, ownerId);
        query.setParameter(DataBaseConstants.USER_ID_FIELD, userId);
        return query.list();
    }

    public void requestAccess(GroupParticipant groupParticipant) {
        groupParticipant.setStatus(DataBaseConstants.USER_REQUESTED);
        super.saveOrUpdateEntity(groupParticipant);
    }

    public void allowAccess(GroupParticipant groupParticipant) {
        groupParticipant.setStatus(DataBaseConstants.USER_ACCEPTED);
        super.saveOrUpdateEntity(groupParticipant);
    }

    public void denyAccess(GroupParticipant groupParticipant) {
        groupParticipant.setStatus(DataBaseConstants.USER_REJECTED);
        super.saveOrUpdateEntity(groupParticipant);
    }

    public List<Group> getGroupsByParticipant(long participantId) {
        SQLQuery query = super.getSession().createNativeQuery(GROUP_PARTICIPANT_QUERY);
        query.addEntity(Group.class);
        query.setParameter(DataBaseConstants.USER_ID_FIELD, participantId);
        return query.list();
    }

    public Group getGroup(Group group) {
        return super.findById(Group.class, group.getId());
    }

    public Group getFullGroup(Group group) {
        group = getGroup(group);
        group.setRequesters(userDao.getRequestersGroup(group));
        group.setParticipants(userDao.getParticipantsGroup(group));
        return group;
    }
}