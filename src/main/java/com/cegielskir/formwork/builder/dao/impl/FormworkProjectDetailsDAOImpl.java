package com.cegielskir.formwork.builder.dao.impl;

import com.cegielskir.formwork.builder.dao.FormworkProjectDetailsDAO;
import com.cegielskir.formwork.builder.entity.FormworkProject;
import com.cegielskir.formwork.builder.entity.FormworkProjectDetails;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FormworkProjectDetailsDAOImpl implements FormworkProjectDetailsDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void add(FormworkProjectDetails formworkProjectDetails) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(formworkProjectDetails);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        FormworkProjectDetails formworkProjectDetails = currentSession.load(FormworkProjectDetails.class, id);
        currentSession.delete(formworkProjectDetails);

    }

    @Override
    public FormworkProjectDetails getById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(FormworkProjectDetails.class, id);
    }
}
