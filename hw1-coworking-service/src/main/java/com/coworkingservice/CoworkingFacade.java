package com.coworkingservice;

import com.coworkingservice.fabric.EntityFabric;
import com.coworkingservice.memorydb.FreeSlotsCRUD;
import com.coworkingservice.memorydb.PersonCRUD;
import com.coworkingservice.memorydb.ReservedSlotsCRUD;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.service.booking.BookingSlot;
import com.coworkingservice.service.verify.VerifyDate;

public class CoworkingFacade {
    private final EntityFabric entityFabric;
    private final VerifyDate verifyDate;
    private final RoomCRUD roomCRUD;
    private final PersonCRUD personCRUD;
    private final ReservedSlotsCRUD reservedSlotsCRUD;
    private final FreeSlotsCRUD freeSlotsCRUD;
    private final GeneralInterface generalInterface;
    public CoworkingFacade() {
        this.entityFabric = new EntityFabric();
        this.verifyDate = new VerifyDate();
        this.roomCRUD = new RoomCRUD(entityFabric);
        this.personCRUD = new PersonCRUD(entityFabric);
        this.reservedSlotsCRUD = new ReservedSlotsCRUD(roomCRUD,new BookingSlot(verifyDate), entityFabric);
        this.freeSlotsCRUD = new FreeSlotsCRUD(roomCRUD, reservedSlotsCRUD,verifyDate);
        this.generalInterface = new GeneralInterface(freeSlotsCRUD, personCRUD, reservedSlotsCRUD, roomCRUD, entityFabric);
    }
    public void start(){
        generalInterface.startCoworkingService();
    }
}
