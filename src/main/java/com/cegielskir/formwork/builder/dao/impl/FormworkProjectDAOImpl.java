package com.cegielskir.formwork.builder.dao.impl;


import com.cegielskir.formwork.builder.dao.FormworkProjectDAO;
import com.cegielskir.formwork.builder.entity.Formwork;
import com.cegielskir.formwork.builder.entity.FormworkProject;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FormworkProjectDAOImpl implements FormworkProjectDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void add(FormworkProject formworkProject) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(formworkProject);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        FormworkProject formworkProject = currentSession.load(FormworkProject.class, id);
        currentSession.delete(formworkProject);
    }

    @Override
    public FormworkProject getById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(FormworkProject.class, id);
    }
}
