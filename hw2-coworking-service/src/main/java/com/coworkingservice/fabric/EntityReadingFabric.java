package com.coworkingservice.fabric;

import com.coworkingservice.entity.*;
import com.coworkingservice.service.ScannerSingleton;

import java.time.LocalDate;
import java.util.Scanner;

public class EntityReadingFabric implements EntityFamilyReadingFabric{
    private final Scanner scanner;
    public EntityReadingFabric() {
        scanner = ScannerSingleton.getInstance().getScanner();
    }
    @Override
    public Credential createCredential() {
        return null;
    }

    @Override
    public Person createPerson() {
        return null;
    }

    @Override
    public Slot createSlot(int room, int person, LocalDate localDate) {
        return null;
    }

    @Override
    public Room createRoom(int auditorium, String roomName) {
        return switch (roomName) {
            case "Conference room" -> new WorkplaceRoom(auditorium);
            case "Workplace" -> new ConferenceRoom(auditorium);
            default -> throw new IllegalStateException("Unexpected value: " + roomName);
        };
    }
}
