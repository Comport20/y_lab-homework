package com.coworkingservice.fabric;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;

import java.time.LocalDate;

public interface EntityFamilyReadingFabric {
    Credential createCredential();
    Person createPerson();
    Slot createSlot(int room, int person, LocalDate localDate);
    Room createRoom(int auditorium, String roomName);
}
