package com.cegielskir.formwork.builder.service.impl;

import com.cegielskir.formwork.builder.dao.RoomDAO;
import com.cegielskir.formwork.builder.entity.Room;
import com.cegielskir.formwork.builder.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    RoomDAO roomDAO;

    @Override
    @Transactional
    public void add(Room room) {
        roomDAO.add(room);
    }

    @Override
    @Transactional
    public List<Room> getList() {
        return roomDAO.getList();
    }

    @Override
    @Transactional
    public void delete(int id) {
        roomDAO.delete(id);
    }

    @Override
    @Transactional
    public Room getById(int id) {
        return roomDAO.getById(id);
    }
}
