package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;

import java.util.*;

public class MemoryDB {
    private static MemoryDB instance;
    private final Map<Long, Room> roomMapTable;
    private final Map<Credential, Person> personMapTable;
    private final List<Room> freeRoomsListTable;
    private final List<Room> reservedRoomsListTable;

    private MemoryDB() {
        this.roomMapTable = new TreeMap<>();
        this.personMapTable = new HashMap<>();
        this.freeRoomsListTable = new LinkedList<>();
        this.reservedRoomsListTable = new LinkedList<>();
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

    public List<Room> getFreeRoomsListTable() {
        return freeRoomsListTable;
    }

    public List<Room> getReservedRoomsListTable() {
        return reservedRoomsListTable;
    }
}
