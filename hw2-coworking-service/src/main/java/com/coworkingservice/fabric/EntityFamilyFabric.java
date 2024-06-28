package com.coworkingservice.fabric;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;

import java.time.LocalDate;

public interface EntityFamilyFabric {
    Credential createCredential();
    Person createPerson();
    Slot createSlot(Room room, Person person, LocalDate localDate);
    Room createRoom(Long roomId);

}
