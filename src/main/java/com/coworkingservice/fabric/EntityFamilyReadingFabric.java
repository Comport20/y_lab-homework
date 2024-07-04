package com.coworkingservice.fabric;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;

import java.sql.Timestamp;

public interface EntityFamilyReadingFabric {
    Credential createCredential(String username, String password, Person person);
    Person createPerson(int id, String firstname, String lastname, String email);
    Slot createSlot(Room room, double price, Person person, Timestamp fromLocalDate, Timestamp toLocalDate);
    Room createRoom(int id, int auditorium, String roomName);
}
