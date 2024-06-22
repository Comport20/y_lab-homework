package com.coworkingservice;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.fabric.TemporaryFabric;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.service.ScannerSingleton;
import com.coworkingservice.service.filter.PersonFilter;
import com.coworkingservice.service.filter.PriceFilter;
import com.coworkingservice.service.filter.TimeFilter;


import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Scanner;


public class DashboardFacade {

    private final FreeSlotsCRUD freeSlotsCRUD;
    private final PersonCRUD personCRUD;
    private final ReservedSlots reservedSlots;
    private final RoomCRUD roomCRUD;
    private final Scanner scanner;
    private Person person;
    private boolean auth = false;
    private boolean exit = false;
    private final TemporaryFabric temporaryFabric;

    public DashboardFacade(FreeSlotsCRUD freeSlotsCRUD, PersonCRUD personCRUD, ReservedSlots reservedSlots, RoomCRUD roomCRUD, TemporaryFabric temporaryFabric) {
        this.freeSlotsCRUD = freeSlotsCRUD;
        this.personCRUD = personCRUD;
        this.reservedSlots = reservedSlots;
        this.roomCRUD = roomCRUD;
        this.scanner = ScannerSingleton.getInstance().getScanner();
        this.temporaryFabric = temporaryFabric;
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
        person = personCRUD.read(temporaryFabric.createCredential());
        if (person != null) {
            System.out.println("The user has been successfully logged in");
            auth = true;
        }else {
            System.out.println("This user was not found");
        }
    }

    private void registration() {
        Credential credential = temporaryFabric.createCredential();
        personCRUD.create(credential);
        person = personCRUD.read(credential);
        auth = true;
    }

    private void reviewPlaces() {
        boolean isEditEnd = false;
        while (!isEditEnd) {
            roomCRUD.readAll();
            System.out.println("To add an audience, press 1");
            System.out.println("To change the audience, press 2");
            System.out.println("To delete an audience, press 3");
            System.out.println("Back - 0");
            switch (scanner.nextInt()) {
                case 0 -> isEditEnd = true;
                case 1 -> roomCRUD.create();
                case 2 -> {
                    System.out.println("Enter the number of the audience you want to change");
                    Room room = roomCRUD.read(scanner.nextLong());
                    if (room != null)
                        roomCRUD.update(room.getRoomId());
                    else
                        System.out.println("I entered the number incorrectly");
                }
                case 3 -> {
                    System.out.println("Enter the number of the audience you want to delete");
                    Room room = roomCRUD.read(scanner.nextLong());
                    if (room != null)
                        roomCRUD.delete(room.getRoomId());
                    else
                        System.out.println("The number is entered incorrectly");
                }
            }
        }
    }

    private void availableSlots() {
        roomCRUD.readAll();
        System.out.println("Select an audience for booking: ");
        long roomId = scanner.nextLong();
        System.out.println("Which day for booking are you interested in (format for entering YYYY-MM-DD): ");
        String date = scanner.next();
        try {
            LocalDate localDate = LocalDate.parse(date);
            freeSlotsCRUD.readAll(roomId, person, localDate);
        } catch (Exception e) {
            System.out.println("Something went wrong, make sure you entered the date correctly");
        }
    }

    private void reservedPlaces() {
        boolean reservedPlacesExit = false;
        while (!reservedPlacesExit) {
            reservedSlots.readAll();
            System.out.println("To cancel a reservation, press 1");
            System.out.println("To sort by date, press 2");
            System.out.println("To sort by resource, press 3");
            System.out.println("To sort by user, press 4");
            System.out.println("Back - 0");
            switch (scanner.nextInt()) {
                case 0 -> reservedPlacesExit = true;
                case 1 -> undoneBooking();
                case 2 -> MemoryDB.getInstance().getReservedSlotListTable().sort(new TimeFilter());
                case 3 -> MemoryDB.getInstance().getReservedSlotListTable().sort(new PriceFilter());
                case 4 -> MemoryDB.getInstance().getReservedSlotListTable().sort(new PersonFilter());
            }
        }
    }

    private void undoneBooking() {
        System.out.println("Enter the audience number: ");
        long roomId = scanner.nextLong();
        System.out.println("Enter the time in the YYYY-MM-DDThh:mm:ss format from which the booking begins: ");
        String localDateTimeStr = scanner.next();
        try {
            LocalDateTime fromLocalDateTime = LocalDateTime.parse(localDateTimeStr);
            reservedSlots.delete(roomId, fromLocalDateTime);
        } catch (Exception e) {
            System.out.println("Something went wrong, make sure you entered the date and time correctly");
            e.printStackTrace();
        }
    }
}
