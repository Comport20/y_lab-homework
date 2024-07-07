package com.coworkingservice;


import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.fabric.*;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.memorydb.credential.CredentialRead;
import com.coworkingservice.memorydb.person.PersonCreate;
import com.coworkingservice.memorydb.person.PersonRead;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotReadWhereIdAndDate;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotsCreate;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotsDeleteImp;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotsRead;
import com.coworkingservice.memorydb.room.RoomCreate;
import com.coworkingservice.memorydb.room.RoomDelete;
import com.coworkingservice.memorydb.room.RoomRead;
import com.coworkingservice.memorydb.room.RoomUpdate;
import com.coworkingservice.service.verify.FreeSlotsCheck;
import com.coworkingservice.service.verify.VerifyDate;

public class CoworkingFacade {
    private final GeneralInterface generalInterface;

    public CoworkingFacade() {
        EntityFamilyFabric entityFamilyFabric = new EntityFabric();
        RoomCRUDAbstractFabric roomCRUDAbstractFabric = new RoomCRUDAbstractFabricBaseImp();
        PersonCRUDAbstractFabric personCRUDAbstractFabric = new PersonCRUDAbstractFabricBaseImp();
        CredentialCRUDAbstractFabric credentialCRUDAbstractFabric = new CredentialCRUDAbstractFabricBaseImp();
        ReservedSlotsCRUDAbstractFabric reservedSlotsCRUDAbstractFabric = new ReservedSlotsCRUDAbstractFabricBaseImp();
        RoomCRUD roomCRUD = new RoomCRUD(roomCRUDAbstractFabric);
        PersonCRUD personCRUD = new PersonCRUD(personCRUDAbstractFabric);
        CredentialCRUD credentialCRUD = new CredentialCRUD(credentialCRUDAbstractFabric);
        ReservedSlotsCRUD reservedSlotsCRUD = new ReservedSlotsCRUD(reservedSlotsCRUDAbstractFabric);
        FreeSlotsCheck freeSlotsCheck = new FreeSlotsCheck(entityFamilyFabric, roomCRUD, reservedSlotsCRUD, new VerifyDate(reservedSlotsCRUD));
        this.generalInterface = new GeneralInterface(freeSlotsCheck, personCRUD, reservedSlotsCRUD,
                roomCRUD, entityFamilyFabric, credentialCRUD);
    }

    public void start() {
        generalInterface.startCoworkingService();
    }
}
