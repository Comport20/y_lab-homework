package com.coworkingservice.memorydb;

import com.coworkingservice.entity.*;

import java.util.*;

public class MemoryDB {
    private static MemoryDB instance;
    private final Map<Long, Room> roomMapTable;
    private final Map<Credential, Person> personMapTable;
    private final List<Slot> freeSlotListTable;
    private final List<Slot> reservedSlotListTable;

    private MemoryDB() {
        this.roomMapTable = new TreeMap<>();
        this.personMapTable = new HashMap<>();
        this.freeSlotListTable = new LinkedList<>();
        this.reservedSlotListTable = new LinkedList<>();
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

    public Map<Long, Room> getRoomMapTable() {
        return roomMapTable;
    }

    public Map<Credential, Person> getPersonMapTable() {
        return personMapTable;
    }

    public List<Slot> getFreeSlotListTable() {
        return freeSlotListTable;
    }

    public List<Slot> getReservedSlotListTable() {
        return reservedSlotListTable;
    }
}
