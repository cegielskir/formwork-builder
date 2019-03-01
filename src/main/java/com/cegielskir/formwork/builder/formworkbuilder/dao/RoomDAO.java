package com.cegielskir.formwork.builder.formworkbuilder.dao;

import com.cegielskir.formwork.builder.formworkbuilder.entity.GirderSet;
import com.cegielskir.formwork.builder.formworkbuilder.entity.Room;

import java.util.List;

public interface RoomDAO {
    void add(Room room);
    List<Room> getList();
    void delete(int id);
    Room getById(int id);
}
