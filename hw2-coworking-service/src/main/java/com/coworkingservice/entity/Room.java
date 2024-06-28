package com.coworkingservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Room {
    protected long roomId;
    protected String roomName;
    @Setter
    protected double price;

    public Room(long roomId, String roomName, double price) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.price = price;
    }

}
