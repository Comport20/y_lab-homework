package com.coworkingservice.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public abstract class Room {
    @Setter(AccessLevel.NONE)
    private int id;
    private int auditorium;
    protected String roomName;
    protected double price;

    public Room(int auditorium, String roomName, double price) {
        this.auditorium = auditorium;
        this.roomName = roomName;
        this.price = price;
    }
    public Room(int id, int auditorium, String roomName, double price) {
        this.id = id;
        this.auditorium = auditorium;
        this.roomName = roomName;
        this.price = price;
    }
}
