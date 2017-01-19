package com.nojsoft.dao;

import com.nojsoft.model.BaseModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by alan on 1/14/17.
 */
public abstract class GeneralDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public <T extends BaseModel> T saveOrUpdateEntity(T model) {
        getSession().saveOrUpdate(model);
        return model;
    }

    public void deleteEntity(BaseModel model) {
        getSession().delete(model);
    }
}