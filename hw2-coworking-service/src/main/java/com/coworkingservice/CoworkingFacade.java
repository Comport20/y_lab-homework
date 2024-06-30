package com.coworkingservice;

import com.coworkingservice.fabric.EntityFabric;
import com.coworkingservice.fabric.EntityReadingFabric;
import com.coworkingservice.memorydb.PersonCRUD;
import com.coworkingservice.memorydb.ReservedSlotsCRUD;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.memorydb.RoomCreate;
import com.coworkingservice.service.booking.BookingSlot;
import com.coworkingservice.service.verify.FreeSlotsCheck;
import com.coworkingservice.service.verify.VerifyDate;

public class CoworkingFacade {
    private EntityFabric entityFabric;
    private VerifyDate verifyDate;
    private RoomCRUD roomCRUD;
    private PersonCRUD personCRUD;
    private ReservedSlotsCRUD reservedSlotsCRUD;
    private FreeSlotsCheck freeSlotsCheck;
    private GeneralInterface generalInterface;
    private EntityReadingFabric entityReadingFabric;

    public CoworkingFacade() {

    }

    public void start() {
        generalInterface.startCoworkingService();
    }
}
