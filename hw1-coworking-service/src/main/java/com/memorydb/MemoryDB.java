package com.memorydb;

import com.entity.Room;

import java.util.HashMap;
import java.util.Map;

public class MemoryDB {
    private static MemoryDB instance;
    private final Map<Long, Room> roomMap;

    private MemoryDB() {
        this.roomMap = new HashMap<Long, Room>();
    }

    public static MemoryDB getInstance() {
        if (instance == null) {
            synchronized (MemoryDB.class) {
                if (instance == null) {
                    instance = new MemoryDB();
                }
            }
        }
        return instance;
    }

    public Map<Long, Room> getRoomMap() {
        return roomMap;
    }
}
