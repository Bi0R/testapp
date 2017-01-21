package com.nojsoft.dao;

import com.nojsoft.constants.DataBaseConstants;
import com.nojsoft.model.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan on 1/14/17.
 */

@Repository("GroupDao")
public class GroupDao extends GeneralDao {

    public Group saveOrUpdate(Group group) {
        return saveOrUpdateEntity(group);
    }

    public void delete(Group group) {
        deleteEntity(group);
    }

    public List<Group> getGroupsByOwner(long ownerId) {
        return findByField(DataBaseConstants.OWNER_ID_FIELD, ownerId);
    }
}