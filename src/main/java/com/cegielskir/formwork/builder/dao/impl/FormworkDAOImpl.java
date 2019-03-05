package com.cegielskir.formwork.builder.dao.impl;

import com.cegielskir.formwork.builder.dao.FormworkDAO;
import com.cegielskir.formwork.builder.entity.Formwork;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class FormworkDAOImpl implements FormworkDAO{

    private EntityManager entityManager;

    @Autowired
    public FormworkDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public void add(Formwork formwork) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(formwork);
    }

    @Override
    public List<Formwork> getList() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Formwork> theQuery = currentSession.createQuery("from Formwork", Formwork.class);
        return theQuery.getResultList();
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Formwork formwork = currentSession.load(Formwork.class, id);
        currentSession.delete(formwork);
    }


    @Override
    public Formwork getById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Formwork.class, id);
    }
}
