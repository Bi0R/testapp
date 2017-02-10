package com.nojsoft.dao;

import com.nojsoft.constants.DataBaseConstants;
import com.nojsoft.model.Group;
import com.nojsoft.model.GroupParticipant;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alan on 1/14/17.
 */

@Repository("GroupDao")
public class GroupDao extends GeneralDao {

    public static String GROUP_PARTICIPANT_QUERY = "SELECT g.* from groups g " +
            "JOIN group_participants gp ON g.id = gp.group_id WHERE gp.status = 1 AND gp.user_id =:userId";

    public static String GROUP_OWNER_SEARCH = "SELECT g.* FROM groups g" +
                         "JOIN group_participants gp " +
                         "ON g.id = gp.group_id AND gp.status NOT IN (0,1) AND gp.user_id = :userId" +
                         "WHERE  g.owner_id = :ownerId AND g.owner_id <> :userId" +
                         "UNION ALL" +
                         "SELECT g.* FROM groups g" +
                         "LEFT JOIN group_participants gp ON g.id = gp.group_id" +
                         "WHERE  g.owner_id = :ownerId AND g.owner_id <> :userId AND gp.group_id IS NULL;";


    public Group saveOrUpdate(Group group) {
        return super.saveOrUpdateEntity(group);
    }

    public void delete(Group group) {
        super.deleteEntity(group);
    }

    public List<Group> getGroupsByOwner(long ownerId) {
        Map<String, Object> fieldsValues = new HashMap<String, Object>();
        fieldsValues.put(DataBaseConstants.OWNER_ID_FIELD, ownerId);
        fieldsValues.put(DataBaseConstants.STATUS_FIELD,1);
        return super.findBySeveralFields(Group.class, fieldsValues);
    }

    public List<Group> getGroupsByOwnerStatus(long ownerId, long userId) {
        SQLQuery query = super.getSession().createNativeQuery(GROUP_OWNER_SEARCH);
        query.addEntity(Group.class);
        query.setParameter(DataBaseConstants.OWNER_ID_FIELD, ownerId);
        query.setParameter(DataBaseConstants.USER_ID_FIELD, userId);
        System.out.println (query.toString());
        return query.list();
    }

    public void requestAccess(GroupParticipant groupParticipant) {
        groupParticipant.setStatus(DataBaseConstants.USER_REQUESTER);
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
}