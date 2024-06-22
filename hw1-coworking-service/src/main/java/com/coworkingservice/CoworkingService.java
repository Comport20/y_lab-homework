package com.coworkingservice;


import com.coworkingservice.entity.*;
import com.coworkingservice.fabric.TemporaryFabric;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.service.booking.BookingSlot;

import java.util.Map;

public class CoworkingService {
    public static void main(String[] args) {
        Map<Long, Room> testMap = MemoryDB.getInstance().getRoomMapTable();
        Map<Credential, Person> testMap2 = MemoryDB.getInstance().getPersonMapTable();
        testMap2.put(new Credential("login","password"),new Tenant("GG","BB"));
        testMap.put(412L,new WorkplaceRoom(412L));
        testMap.put(233L,new ConferenceRoom(233L));
        testMap.put(344L,new ConferenceRoom(344L));
        testMap.put(322L,new ConferenceRoom(322L));
        testMap.put(321L,new ConferenceRoom(321L));
        testMap.put(340L,new ConferenceRoom(340L));
        RoomCRUD roomCRUD = new RoomCRUD();
        PersonCRUD personCRUD = new PersonCRUD();
        TemporaryFabric temporaryFabric = new TemporaryFabric();
        FreeSlotsCRUD freeSlotsCRUD = new FreeSlotsCRUD(roomCRUD, new BookingSlot());
        ReservedSlots reservedSlots = new ReservedSlots(roomCRUD);
        DashboardFacade dashboardFacade = new DashboardFacade(freeSlotsCRUD, personCRUD, reservedSlots, roomCRUD, temporaryFabric);
        dashboardFacade.startCoworkingService();
    }
}
