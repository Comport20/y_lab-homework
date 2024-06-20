package com.service;

import com.entity.Room;

public class BookingRoom implements Booking {
    private Room reservationRoom;

    public BookingRoom(Room reservationRoom) {
        this.reservationRoom = reservationRoom;
    }

    @Override
    public void book() {

    }
}
