package com.cegielskir.formwork.builder.formworkbuilder.dao.impl;

import com.cegielskir.formwork.builder.formworkbuilder.dao.GirderSetDAO;
import com.cegielskir.formwork.builder.formworkbuilder.entity.GirderSet;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class GirderSetDAOImpl implements GirderSetDAO {
    private EntityManager entityManager;

    @Autowired
    public GirderSetDAOImpl(EntityManager theEntityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(GirderSet girderSet) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(girderSet);
    }

    @Override
    public List<GirderSet> getList(int formworkId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<GirderSet> query = currentSession.createQuery("from GirderSet as gs" +
                                                    "where gs.formworkId = :id ", GirderSet.class);
        return query.getResultList();
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        GirderSet girderSet = currentSession.load(GirderSet.class, id);
        currentSession.delete(girderSet);

    }

    @Override
    public GirderSet getById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(GirderSet.class, id);
    }
}
