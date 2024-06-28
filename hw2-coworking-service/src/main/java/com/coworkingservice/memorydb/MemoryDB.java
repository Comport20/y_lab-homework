package com.coworkingservice.memorydb;

import com.coworkingservice.entity.*;
import lombok.Getter;

import java.util.*;

@Getter
public class MemoryDB {
    private static MemoryDB instance;
    private final Map<Long, Room> roomMapTable;
    private final Map<Credential, Person> personMapTable;
    private final List<Slot> reservedSlotListTable;

    private MemoryDB() {
        this.roomMapTable = new TreeMap<>();
        this.personMapTable = new HashMap<>();
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

}
