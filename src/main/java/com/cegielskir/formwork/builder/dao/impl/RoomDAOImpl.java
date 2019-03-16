package com.cegielskir.formwork.builder.dao.impl;

import com.cegielskir.formwork.builder.dao.RoomDAO;
import com.cegielskir.formwork.builder.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoomDAOImpl implements RoomDAO{
    private EntityManager entityManager;

    @Autowired
    public RoomDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public void add(Room room) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(room);
    }

    @Override
    public List<Room> getList() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Room> query = currentSession.createQuery("from Room r");
        return query.getResultList();
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Room room = currentSession.load(Room.class, id);
        currentSession.delete(room);

    }

    @Override
    public Room getById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Room.class, id);
    }
}
