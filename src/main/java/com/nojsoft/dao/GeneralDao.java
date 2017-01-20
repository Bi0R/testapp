package com.nojsoft.dao;

import com.nojsoft.model.BaseModel;
import com.nojsoft.model.Group;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by alan on 1/14/17.
 */
public abstract class GeneralDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    <T extends BaseModel> T saveOrUpdateEntity(T model) {
        getSession().saveOrUpdate(model);
        return model;
    }

    void deleteEntity(BaseModel model) {
        getSession().delete(model);
    }

    <T extends BaseModel> List<T> findByField(String field, Object value) {
        Criteria criteria = getSession().createCriteria(BaseModel.class);
        return criteria.add(Restrictions.eq(field, value)).list();
    }
}