package com.nojsoft.dao;

import com.nojsoft.model.Group;
import org.springframework.stereotype.Repository;

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
}