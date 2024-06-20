package com.entity;

public abstract class Room {
    protected long roomId;
    protected String roomName;

    public Room(long roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public long getRoomId() {
        return roomId;
    }
}
