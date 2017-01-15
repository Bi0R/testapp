package com.nojsoft.dao;

import com.nojsoft.model.Group;

/**
 * Created by alan on 1/14/17.
 */
public class GroupDao extends GeneralDao {

    public static Group saveOrUpdate(Group group) {
        return saveOrUpdateEntity(group);
    }

    public static void delete(Group group) {
        deleteEntity(group);
    }
}