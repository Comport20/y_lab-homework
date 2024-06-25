package com.coworkingservice.fabric;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;

import java.time.LocalDate;

public abstract class EntityFamilyFabric {
    public abstract Credential createCredential();
    public abstract Person createPerson();
    public abstract Slot createSlot(Room room, Person person, LocalDate localDate);
    public abstract Room createRoom(Long roomId);

}
