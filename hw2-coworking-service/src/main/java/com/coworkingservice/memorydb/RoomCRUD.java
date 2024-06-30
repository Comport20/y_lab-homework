package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Room;

import java.util.List;



public class RoomCRUD {
    private final Create<Room> roomCreate;
    private final Read<Room> roomRead;
    private final Delete roomDelete;
    private final Update<Room> roomUpdate;
    public RoomCRUD(Create<Room> roomCreate, Read<Room> roomRead, Delete roomDelete, Update<Room> roomUpdate) {
        this.roomCreate = roomCreate;
        this.roomRead = roomRead;
        this.roomDelete = roomDelete;
        this.roomUpdate = roomUpdate;
    }

    public void create(Room room) {
        roomCreate.create(room);
    }


    public Room readWhere(int auditorium) {
        return roomRead.readWhere(auditorium);
    }

    public List<Room> readAll() {
        return roomRead.readAll();
    }


    public boolean update(int auditorium, Room room) {
        return roomUpdate.update(auditorium, room);
    }


    public boolean delete(int auditorium) {
        return roomDelete.delete(auditorium);
    }
}
