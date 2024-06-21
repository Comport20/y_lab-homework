package com.coworkingservice;


import com.coworkingservice.entity.ConferenceRoom;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.WorkplaceRoom;
import com.coworkingservice.memorydb.MemoryDB;
import com.coworkingservice.memorydb.RoomCRUD;

import java.util.Map;

public class CoworkingService {
    public static void main(String[] args) {
        Map<Long, Room> testMap = MemoryDB.getInstance().getRoomMapTable();
        testMap.put(1132L,new WorkplaceRoom(1));
        testMap.put(2L,new ConferenceRoom(2));
        testMap.put(3L,new ConferenceRoom(3));
        RoomCRUD roomCRUD = new RoomCRUD();
        roomCRUD.readAll();
    }
}
