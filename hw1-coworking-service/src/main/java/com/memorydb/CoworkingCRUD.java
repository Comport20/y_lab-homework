package com.memorydb;

import com.entity.Room;

public class CoworkingCRUD implements CRUD{
    private Room room;
    public CoworkingCRUD(Room room) {
        this.room = room;
    }
    @Override
    public void create() {

    }

    @Override
    public void read() {

    }

    @Override
    public void readAll() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
