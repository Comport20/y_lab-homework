package com.coworkingservice.entity;

public abstract class Room {
    protected long roomId;
    protected String roomName;
    protected double price;


    public Room(long roomId, String roomName, double price) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.price = price;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
