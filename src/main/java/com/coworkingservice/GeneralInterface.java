package com.coworkingservice;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.service.ScannerSingleton;
import com.coworkingservice.service.verify.FreeSlotsCheck;


import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Scanner;


public class GeneralInterface {

    private final FreeSlotsCheck freeSlotsCheck;
    private final CredentialCRUD credentialCRUD;
    private final PersonCRUD personCRUD;
    private final ReservedSlotCRUD reservedSlotCRUD;
    private final RoomCRUD roomCRUD;
    private final Scanner scanner;
    private Person person;
    private boolean auth = false;
    private boolean exit = false;
    private final EntityFamilyFabric entityFabric;

    public GeneralInterface(FreeSlotsCheck freeSlotsCheck, PersonCRUD personCRUD, ReservedSlotCRUD reservedSlotCRUD, RoomCRUD roomCRUD, EntityFamilyFabric entityFabric, CredentialCRUD credentialCRUD) {
        this.freeSlotsCheck = freeSlotsCheck;
        this.personCRUD = personCRUD;
        this.reservedSlotCRUD = reservedSlotCRUD;
        this.roomCRUD = roomCRUD;
        this.scanner = ScannerSingleton.getInstance().getScanner();
        this.entityFabric = entityFabric;
        this.credentialCRUD = credentialCRUD;
    }

    public void startCoworkingService() {
        while (!exit) {
            if (!auth) {
                System.out.println("Hello!\n");
                System.out.printf("%-20s\n", "To log in, press 1");
                System.out.printf("%-20s\n", "To register, press 2");
                System.out.printf("%-20s\n", "To exit, press 0");
                switch (scanner.nextInt()) {
                    case 0 -> exit = true;
                    case 1 -> auth();
                    case 2 -> registration();
                    default -> System.out.println("Input error try again");
                }
            } else {
                System.out.printf("%-60s\n", "To view workplaces and conference rooms, press 1");
                System.out.printf("%-60s\n", "To view an available booking 2");
                System.out.printf("%-60s\n", "To view all bookings, press 3");
                System.out.printf("%-60s\n", "To log out of your account, press 6");
                System.out.printf("%-20s\n", "To exit, press 0");
                switch (scanner.nextInt()) {
                    case 0 -> exit = true;
                    case 1 -> reviewPlaces();
                    case 2 -> availableSlots();
                    case 3 -> reservedPlaces();
                    case 6 -> auth = false;
                    default -> System.out.println("Input error try again");
                }
            }
        }
    }

    private void auth() {
        System.out.printf("%-20s", "Enter your username: ");
        String login = scanner.next();
        System.out.printf("%-20s", "Enter the Password: ");
        String password = scanner.next();
        int id = credentialCRUD.readWhereEntity(new Credential(login, password));
        if (id != -1) {
            this.person = personCRUD.read(id);
            System.out.println("The user has been successfully logged in");
            auth = true;
        } else {
            System.out.println("This user was not found");
        }
    }

    private void registration() {
        Person person = entityFabric.createPerson();
        personCRUD.create(person);
        this.person = personCRUD.readWhereString(person.getEmail());
        System.out.println("The user has been successfully registered");
        auth = true;
    }

    private void reviewPlaces() {
        boolean isEditEnd = false;
        while (!isEditEnd) {
            readAllRoom();
            System.out.println("To add an auditorium, press 1");
            System.out.println("To change the auditorium, press 2");
            System.out.println("To delete an auditorium, press 3");
            System.out.println("Back - 0");
            switch (scanner.nextInt()) {
                case 0 -> isEditEnd = true;
                case 1 -> roomCRUD.create(entityFabric.createRoom());
                case 2 -> {
                    System.out.println("Enter the number of the auditorium you want to change");
                    if (roomCRUD.update(scanner.nextInt(), entityFabric.createRoom()))
                        System.out.println("The auditorium has changed");
                    else
                        System.out.println("I entered the number incorrectly");
                }
                case 3 -> {
                    System.out.println("Enter the number of the auditorium you want to delete");
                    if (roomCRUD.delete(scanner.nextInt()))
                        System.out.println("The auditorium has been deleted");
                    else
                        System.out.println("The number is entered incorrectly");
                }
            }
        }
    }

    private void availableSlots() {
        readAllRoom();
        System.out.println("Select an auditorium for booking: ");
        int auditorium = scanner.nextInt();
        System.out.println("Which day for booking are you interested in (format for entering YYYY-MM-DD): ");
        String date = scanner.next();
        try {
            LocalDate localDate = LocalDate.parse(date);
            freeSlotsCheck.readAll(auditorium, person, localDate);
        } catch (Exception e) {
            System.out.println("Something went wrong, make sure you entered the date correctly");
        }
    }

    private void reservedPlaces() {
        boolean reservedPlacesExit = false;
        while (!reservedPlacesExit) {
            readAllReservedSlots();
            System.out.println("To cancel a reservation, press 1");
            System.out.println("To sort by date, press 2");
            System.out.println("To sort by resource, press 3");
            System.out.println("To sort by user, press 4");
            System.out.println("Back - 0");
            switch (scanner.nextInt()) {
                case 0 -> reservedPlacesExit = true;
                case 1 -> undoneBooking();
                case 2 -> throw new RuntimeException();
                case 3 -> throw new RuntimeException();
                case 4 -> throw new RuntimeException();
            }
        }
    }

    private void undoneBooking() {
        System.out.println("Enter the auditorium number: ");
        int roomId = scanner.nextInt();
        System.out.println("Enter the time in the YYYY-MM-DDThh:mm:ss format from which the booking begins: ");
        String localDateTimeStr = scanner.next();
        LocalDateTime fromLocalDateTime = LocalDateTime.parse(localDateTimeStr);
        if (!reservedSlotCRUD.delete(roomId, fromLocalDateTime))
            System.out.println("Something went wrong, make sure you entered the date and time correctly");

    }

    private void readAllRoom() {
        List<Room> roomsList = roomCRUD.readAll();
        System.out.printf("%-10s  %-20s  %-10s\n", "№", "Room", "From");
        for (Room entry : roomsList) {
            System.out.printf("%-10s  %-20s  %-10s\n", entry.getAuditorium(),
                    entry.getRoomName(), entry.getPrice() + " rub.");
        }
    }

    private void readAllReservedSlots() {
        List<Slot> slotsList = reservedSlotCRUD.readAll();
        System.out.printf("%-10s %-20s %-40s %-10s %-20s %-20s\n", "№", "Room", "Person", "Price", "From", "To");
        for (Slot slot : slotsList) {
            System.out.printf("%-10s %-20s %-40s %-10s %-20s %-20s\n",
                    slot.getAuditorium(), slot.getRoomName(), slot.getPersonName(),slot.getPrice(),
                    slot.getFromLocalDateTime(), slot.getToLocalDateTime());
        }
    }
}
