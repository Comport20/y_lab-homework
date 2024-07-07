package com.coworkingservice;


import com.coworkingservice.fabric.*;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.service.verify.FreeSlotsCheck;
import com.coworkingservice.service.verify.VerifyDate;

public class CoworkingFacade {
    private final GeneralInterface generalInterface;

    public CoworkingFacade() {
        EntityFamilyFabric entityFamilyFabric = new EntityFabric();
        RoomCRUDAbstractFabric roomCRUDAbstractFabric = new RoomCRUDAbstractFabricBaseImp();
        PersonCRUDAbstractFabric personCRUDAbstractFabric = new PersonCRUDAbstractFabricBaseImp();
        CredentialCRUDAbstractFabric credentialCRUDAbstractFabric = new CredentialCRUDAbstractFabricBaseImp();
        ReservedSlotCRUDAbstractFabric reservedSlotCRUDAbstractFabric = new ReservedSlotCRUDAbstractFabricBaseImp();
        RoomCRUD roomCRUD = new RoomCRUD(roomCRUDAbstractFabric);
        PersonCRUD personCRUD = new PersonCRUD(personCRUDAbstractFabric);
        CredentialCRUD credentialCRUD = new CredentialCRUD(credentialCRUDAbstractFabric);
        ReservedSlotCRUD reservedSlotCRUD = new ReservedSlotCRUD(reservedSlotCRUDAbstractFabric);
        FreeSlotsCheck freeSlotsCheck = new FreeSlotsCheck(entityFamilyFabric, roomCRUD, reservedSlotCRUD, new VerifyDate(reservedSlotCRUD));
        this.generalInterface = new GeneralInterface(freeSlotsCheck, personCRUD, reservedSlotCRUD,
                roomCRUD, entityFamilyFabric, credentialCRUD);
    }

    public void start() {
        generalInterface.startCoworkingService();
    }
}
