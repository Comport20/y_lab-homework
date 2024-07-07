package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Room;
import com.coworkingservice.fabric.RoomCRUDAbstractFabric;

import java.util.List;


public class RoomCRUD {
    private final RoomCRUDAbstractFabric roomCRUDAFabric;

    public RoomCRUD(RoomCRUDAbstractFabric roomCRUDAFabric) {
        this.roomCRUDAFabric = roomCRUDAFabric;
    }

    public void create(Room room) {
        roomCRUDAFabric.createCreationMechanism().create(room);
    }


    public Room readWhere(int auditorium) {
        return roomCRUDAFabric.createReadWhereMechanism().readWhere(auditorium);
    }

    public List<Room> readAll() {
        return roomCRUDAFabric.createReadAllMechanism().readAll();
    }


    public boolean update(int auditorium, Room room) {
        return roomCRUDAFabric.createUpdateMechanism().update(auditorium,room);
    }


    public boolean delete(int auditorium) {
        return roomCRUDAFabric.createDeleteMechanism().delete(auditorium);
    }
}
