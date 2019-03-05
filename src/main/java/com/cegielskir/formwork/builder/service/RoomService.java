package com.cegielskir.formwork.builder.service;

import com.cegielskir.formwork.builder.entity.Room;

import java.util.List;

public interface RoomService {
    void add(Room room);
    List<Room> getList();
    void delete(int id);
    Room getById(int id);
}
