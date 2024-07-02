package com.coworkingservice;


import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.fabric.EntityFabric;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;
import com.coworkingservice.fabric.EntityReadingFabric;
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
    private final EntityFamilyFabric entityFamilyFabric;
    private final EntityFamilyReadingFabric entityFamilyReadingFabric;
    private final GeneralInterface generalInterface;
    private final CredentialCRUD credentialCRUD;
    private final PersonCRUD personCRUD;
    private final RoomCRUD roomCRUD;
    private final ReservedSlotsCRUD reservedSlotsCRUD;
    private final FreeSlotsCheck freeSlotsCheck;
    public CoworkingFacade() {
        this.entityFamilyFabric = new EntityFabric();
        this.entityFamilyReadingFabric = new EntityReadingFabric();
        Create<Person> personCreate = new PersonCreate(entityFamilyFabric);
        ReadWhereString<Person> readWhereString = new PersonRead(entityFamilyReadingFabric);
        Read<Person> personRead = new PersonRead(entityFamilyReadingFabric);
        this.personCRUD = new PersonCRUD(personCreate,readWhereString,personRead);
        ReadWhereEntity<Credential> credentialReadWhereEntity = new CredentialRead();
        this.credentialCRUD = new CredentialCRUD(credentialReadWhereEntity);
        Create<Room> roomCreate = new RoomCreate();
        Read<Room> roomRead = new RoomRead(entityFamilyReadingFabric);
        Delete delete = new RoomDelete();
        Update<Room> roomUpdate = new RoomUpdate();
        this.roomCRUD = new RoomCRUD(roomCreate,roomRead,roomUpdate, delete);
        Create<Slot> slotCreate = new ReservedSlotsCreate();
        Read<Slot> slotRead = new ReservedSlotsRead(entityFamilyReadingFabric);
        ReservedSlotsDelete reservedSlotsDelete = new ReservedSlotsDeleteImp();
        this.reservedSlotsCRUD = new ReservedSlotsCRUD(slotCreate,slotRead,reservedSlotsDelete);
        this.freeSlotsCheck = new FreeSlotsCheck(entityFamilyFabric,roomCRUD,reservedSlotsCRUD,new VerifyDate(new ReservedSlotReadWhereIdAndDate(entityFamilyReadingFabric)));
        this.generalInterface = new GeneralInterface(freeSlotsCheck, personCRUD, reservedSlotsCRUD,
                roomCRUD,entityFamilyFabric,credentialCRUD);
    }

    public void start() {
        generalInterface.startCoworkingService();
    }
}
