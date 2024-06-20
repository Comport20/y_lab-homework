package com.service;

import com.entity.Room;

public class VerifyRoom implements Verify{
    private Room room;
    public VerifyRoom(Room room) {
        this.room = room;
    }
    @Override
    public boolean verify() {
        return false;
    }
}
