package com.nojsoft.dao;

import com.nojsoft.constants.DataBaseConstants;
import com.nojsoft.model.Group;
import com.nojsoft.model.GroupParticipant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan on 1/14/17.
 */

@Repository("GroupDao")
public class GroupDao extends GeneralDao {

    public Group saveOrUpdate(Group group) {
        return super.saveOrUpdateEntity(group);
    }

    public void delete(Group group) {
        super.deleteEntity(group);
    }

    public List<Group> getGroupsByOwner(long ownerId) {
        return super.findByField(Group.class, DataBaseConstants.OWNER_ID_FIELD, ownerId);
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
        List <GroupParticipant> groupParticipants =
                super.findByField(GroupParticipant.class, DataBaseConstants.USER_ID_FIELD, participantId);
        List <Long> groups = new ArrayList<Long>();
        if (groupParticipants.isEmpty()) {
            return null;
        }

        for (GroupParticipant groupParticipant : groupParticipants) {
            groups.add(groupParticipant.getGroupId());
        }

        return super.findByField(Group.class, DataBaseConstants.GROUP_ID_FIELD, groups);

    }
}