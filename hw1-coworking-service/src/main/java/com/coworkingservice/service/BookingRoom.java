package com.coworkingservice.service;

import com.coworkingservice.entity.Room;
import com.coworkingservice.memorydb.MemoryDB;

import java.util.List;

public class BookingRoom implements Booking {
    private Room room;
    private List<Room> reservedRoomsListTable;

    public BookingRoom(Room room) {
        this.room = room;
        this.reservedRoomsListTable = MemoryDB.getInstance().getReservedRoomsListTable();
    }

    @Override
    public void book() {
        reservedRoomsListTable.add(room);
    }

    @Override
    public void undone() {
        reservedRoomsListTable.remove(room);
    }
}
