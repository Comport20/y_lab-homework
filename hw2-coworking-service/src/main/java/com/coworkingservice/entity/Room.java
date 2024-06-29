package com.coworkingservice.entity;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public abstract class Room {
    private int auditorium;
    @Setter()
    protected String roomName;
    protected double price;

    public Room(int auditorium, String roomName, double price) {
        this.auditorium = auditorium;
        this.roomName = roomName;
        this.price = price;
    }

}
