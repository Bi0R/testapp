package com.nojsoft.services;

import com.nojsoft.constants.GeneralConstants;
import com.nojsoft.dao.GroupDao;
import com.nojsoft.dao.UserDao;
import com.nojsoft.model.Group;
import com.nojsoft.model.GroupParticipant;
import com.nojsoft.model.GroupSearch;
import com.nojsoft.model.User;
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
    @Autowired
    UserDao userDao;

    public Group saveOrUpdate(Group group) {
        return groupDao.saveOrUpdate(group);
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

    public Group getFullGroup(Group group) {
        return groupDao.getFullGroup(group);
    }

    public List<Group> searchGroup(GroupSearch groupSearch) {
        if (groupSearch.getFilter().equals(GeneralConstants.EMAIL)) {
            User owner = userDao.getUsersByEmail(groupSearch.getValue());
            return owner==null ? null : groupDao.getGroupsByOwnerStatus(owner.getId(),
                    groupSearch.getUserId());
        }
        return null;
    }
}
