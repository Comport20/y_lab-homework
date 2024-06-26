package com.coworkingservice.fabric;

import com.coworkingservice.entity.*;
import com.coworkingservice.service.ScannerSingleton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class EntityFabric extends EntityFamilyFabric{
    private final Scanner scanner;
    public EntityFabric() {
        scanner = ScannerSingleton.getInstance().getScanner();
    }
    public Credential createCredential() {
        System.out.printf("%-20s", "Enter your username: ");
        String login = scanner.next();
        System.out.printf("%-20s", "Enter the Password: ");
        String password = scanner.next();
        return new Credential(login,password);
    }

    public Person createPerson() {
        System.out.printf("%-20s", "Enter your first name: ");
        String firstname = scanner.next();
        System.out.printf("%-20s", "Enter your last name: ");
        String lastname = scanner.next();
        return new Tenant(firstname, lastname);
    }
    public Slot createSlot(Room room, Person person, LocalDate localDate) {
        boolean isCorrect = false;
        int startTime = 0;
        int endTime = 0;
        double price = 0;
        while (!isCorrect) {
            try {
                System.out.println("Enter the time from which you want to make a reservation: ");
                startTime = scanner.nextInt();
                System.out.println("Enter up to how many you want to make a reservation: ");
                endTime = scanner.nextInt();

                if (startTime < endTime && startTime >= 9 && endTime <= 18) {
                    isCorrect = true;
                    price = endTime - startTime;
                } else {
                    System.out.println("The time was entered incorrectly");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input format");
                e.printStackTrace();
            }
        }
        price *= room.getPrice() * price;
        LocalDateTime localDateTime1 = LocalDateTime.of(localDate, LocalTime.of(startTime, 0));
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, LocalTime.of(endTime, 0));
        return new Slot(room, price ,person, localDateTime1, localDateTime2);
    }
    public Room createRoom(Long roomId) {
        System.out.println("To create a workplace, press 1");
        System.out.println("To create a conference room, press 2");
        return switch (scanner.nextInt()) {
            case 1 -> new WorkplaceRoom(roomId);
            case 2 -> new ConferenceRoom(roomId);
            default -> null;
        };
    }
}
