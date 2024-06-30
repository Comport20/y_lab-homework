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
    public Credential createCredential() {
        return null;
    }

    @Override
    public Person createPerson(int id, String firstName, String lastName) {
        return new Tenant(id, firstName, lastName);
    }

    @Override
    public Slot createSlot(Room room, double price, Person person, Timestamp fromLocalDate, Timestamp toLocalDate) {
        return new Slot(room, price, person, fromLocalDate.toLocalDateTime(), toLocalDate.toLocalDateTime());
    }

    @Override
    public Room createRoom(int id, int auditorium, String roomName) {
        return switch (roomName) {
            case "Conference room" -> new WorkplaceRoom(id, auditorium);
            case "Workplace" -> new ConferenceRoom(id, auditorium);
            default -> throw new IllegalStateException("Unexpected value: " + roomName);
        };
    }
}
