package com.cegielskir.formwork.builder.formworkbuilder.service;

import com.cegielskir.formwork.builder.formworkbuilder.entity.Room;

import java.util.List;

public interface RoomService {
    void add(Room room);
    List<Room> getList();
    void delete(int id);
    Room getById(int id);
}
