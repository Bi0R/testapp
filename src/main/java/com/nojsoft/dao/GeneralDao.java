package com.nojsoft.dao;

import com.nojsoft.model.BaseModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * Created by alan on 1/14/17.
 */
public abstract class GeneralDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    <T extends BaseModel> T findById(Class typeClass, long id) {
        return (T) getSession().get(typeClass, id);
    }

    <T extends BaseModel> T saveOrUpdateEntity(T model) {
        getSession().saveOrUpdate(model);
        return model;
    }

    void deleteEntity(BaseModel model) {
        getSession().delete(model);
    }

    <T extends BaseModel> List<T> findByField(Class typeClass, String field, Object value) {
        Criteria criteria = getSession().createCriteria(typeClass);
        return criteria.add(Restrictions.eq(field, value)).list();
    }

    <T extends BaseModel> List<T> findBySeveralFields(Class typeClass, Map<String, Object> fieldValues) {
        Criteria criteria = getSession().createCriteria(typeClass);
        return criteria.add(Restrictions.allEq(fieldValues)).list();
    }

    <T extends BaseModel> List<T> findByFieldValues(Class typeClass, String field, List values) {
        Criteria criteria = getSession().createCriteria(typeClass);
        return criteria.add(Restrictions.in(field, values)).list();
    }

}