package com.coworkingservice;

import com.coworkingservice.fabric.EntityFabric;
import com.coworkingservice.fabric.EntityReadingFabric;
import com.coworkingservice.memorydb.PersonCRUD;
import com.coworkingservice.memorydb.ReservedSlotsCRUD;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.service.booking.BookingSlot;
import com.coworkingservice.service.verify.FreeSlotsCheck;
import com.coworkingservice.service.verify.VerifyDate;

public class CoworkingFacade {
    private final EntityFabric entityFabric;
    private final VerifyDate verifyDate;
    private final RoomCRUD roomCRUD;
    private final PersonCRUD personCRUD;
    private final ReservedSlotsCRUD reservedSlotsCRUD;
    private final FreeSlotsCheck freeSlotsCheck;
    private final GeneralInterface generalInterface;
    private final EntityReadingFabric entityReadingFabric;

    public CoworkingFacade() {
        this.entityFabric = new EntityFabric();
        this.entityReadingFabric = new EntityReadingFabric();
        this.verifyDate = new VerifyDate();
        this.roomCRUD = new RoomCRUD(entityFabric, entityReadingFabric);
        this.personCRUD = new PersonCRUD(entityFabric);
        this.reservedSlotsCRUD = new ReservedSlotsCRUD(roomCRUD, new BookingSlot(verifyDate), entityFabric);
        this.freeSlotsCheck = new FreeSlotsCheck(roomCRUD, reservedSlotsCRUD, verifyDate);
        this.generalInterface = new GeneralInterface(freeSlotsCheck, personCRUD, reservedSlotsCRUD, roomCRUD, entityFabric);
    }

    public void start() {
        generalInterface.startCoworkingService();
    }
}
