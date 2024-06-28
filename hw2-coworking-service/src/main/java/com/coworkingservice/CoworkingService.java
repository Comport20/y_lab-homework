package com.coworkingservice;

import com.coworkingservice.entity.*;
import com.coworkingservice.service.ScannerSingleton;

import java.util.Map;

public class CoworkingService {
    public static void main(String[] args) {
        generateStartingData();
        CoworkingFacade coworkingFacade = new CoworkingFacade();
        coworkingFacade.start();
        ScannerSingleton.getInstance().getScanner().close();
    }

    private static void generateStartingData() {
        long roomId = 1;
        Map<Long, Room> testMap = MemoryDB.getInstance().getRoomMapTable();
        Map<Credential, Person> testMap2 = MemoryDB.getInstance().getPersonMapTable();
        for (int i = 0; i < 10; i++) {
            testMap.put(roomId, new WorkplaceRoom(roomId++));
        }
        for (int i = 0; i < 5; i++) {
            testMap.put(roomId, new ConferenceRoom(roomId++));
        }
        testMap2.put(new Credential("login", "password"), new Tenant("Raw", "User"));
    }
}
