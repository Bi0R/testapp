package com.nojsoft.services;

import com.nojsoft.dao.GroupDao;
import com.nojsoft.model.Group;
import com.nojsoft.model.GroupParticipant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jorge on 01/07/17.
 */
@Service
public class GroupService {

    @Autowired
    GroupDao groupDao;

    public Group saveOrUpdate(Group group) {
        return groupDao.saveOrUpdate(group);
    }

    public List<Group> getGroupsByOwner(long ownerId) {
        return groupDao.getGroupsByOwner(ownerId);
    }

    public void requestAccess(GroupParticipant groupParticipant) {
        groupDao.requestAccess(groupParticipant);
    }

    public void allowAccess(GroupParticipant groupParticipant) {
        groupDao.allowAccess(groupParticipant);
    }

    public void denyAccess(GroupParticipant groupParticipant) {
        groupDao.denyAccess(groupParticipant);
    }

    public List<Group> getGroupsByOwnerStatus(long ownerId, long userId) {
        return groupDao.getGroupsByOwnerStatus(ownerId, userId);
    }

    public List<Group> getGroupsByParticipant(long participantId) {
        return groupDao.getGroupsByParticipant(participantId);
    }

    public Group getFullGroup(Group group) {
        return groupDao.getFullGroup(group);
    }
}
