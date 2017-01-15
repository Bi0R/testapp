package com.nojsoft.dao;

import com.nojsoft.configuration.HibernateConnector;
import com.nojsoft.model.BaseModel;
import org.hibernate.Session;


/**
 * Created by alan on 1/14/17.
 */
class GeneralDao {

    static <T extends BaseModel> T saveOrUpdateEntity(T model) {
        Session session = HibernateConnector.getInstance().getSession();
        session.beginTransaction();
        session.saveOrUpdate(model);
        session.getTransaction().commit();
        session.close();
        return model;
    }

    static void deleteEntity(BaseModel model) {
        Session session = HibernateConnector.getInstance().getSession();
        session.beginTransaction();
        session.delete(model);
        session.getTransaction().commit();
        session.close();
    }
}