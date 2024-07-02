package com.coworkingservice.fabric;

import com.coworkingservice.entity.*;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.service.ScannerSingleton;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;

public class EntityReadingFabric implements EntityFamilyReadingFabric {

    public EntityReadingFabric() {
    }

    @Override
    public Credential createCredential(String username, String password, Person person) {
        return new Credential(username, password, person);
    }

    @Override
    public Person createPerson(int id, String firstName, String lastName, String email) {
        return new Tenant(id, firstName, lastName, email);
    }

    @Override
    public Slot createSlot(Room room, double price, Person person, Timestamp fromLocalDate, Timestamp toLocalDate) {
        return new Slot(room, price, person, fromLocalDate.toLocalDateTime(), toLocalDate.toLocalDateTime());
    }

    @Override
    public Room createRoom(int id, int auditorium, String roomName) {
        return switch (roomName) {
            case "Workplace" -> new WorkplaceRoom(id, auditorium);
            case "Conference room" -> new ConferenceRoom(id, auditorium);
            default -> throw new IllegalStateException("Unexpected value: " + roomName);
        };
    }
}
