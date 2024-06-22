package com.coworkingservice;


import com.coworkingservice.entity.*;
import com.coworkingservice.fabric.TemporaryFabric;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.service.booking.BookingSlot;

import java.util.Map;

public class CoworkingService {
    public static void main(String[] args) {
        generateStartingData();
        RoomCRUD roomCRUD = new RoomCRUD();
        PersonCRUD personCRUD = new PersonCRUD();
        TemporaryFabric temporaryFabric = new TemporaryFabric();
        FreeSlotsCRUD freeSlotsCRUD = new FreeSlotsCRUD(roomCRUD, new BookingSlot());
        ReservedSlots reservedSlots = new ReservedSlots(roomCRUD);
        DashboardFacade dashboardFacade = new DashboardFacade(freeSlotsCRUD, personCRUD, reservedSlots, roomCRUD, temporaryFabric);
        dashboardFacade.startCoworkingService();
    }
    private static void generateStartingData(){
        long roomId = 1;
        Map<Long, Room> testMap = MemoryDB.getInstance().getRoomMapTable();
        Map<Credential, Person> testMap2 = MemoryDB.getInstance().getPersonMapTable();
        for(int i = 0; i < 10; i++){
            testMap.put(roomId,new WorkplaceRoom(roomId++));
        }
        for(int i = 0; i < 5; i++){
            testMap.put(roomId,new ConferenceRoom(roomId++));
        }
        testMap2.put(new Credential("login","password"), new Tenant("Raw","User"));
    }
}
