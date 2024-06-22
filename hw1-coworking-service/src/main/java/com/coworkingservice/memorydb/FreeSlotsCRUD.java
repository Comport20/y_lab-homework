package com.coworkingservice.memorydb;


import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.service.ScannerSingleton;
import com.coworkingservice.service.booking.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class FreeSlotsCRUD {
    private final RoomCRUD roomCRUD;
    private final Scanner scanner;
    private final List<Slot> reservedSlots;
    private final Booking<Slot> booking;

    public FreeSlotsCRUD(RoomCRUD roomCRUD, Booking<Slot> booking) {
        this.roomCRUD = roomCRUD;
        this.scanner = ScannerSingleton.getInstance().getScanner();
        this.reservedSlots = MemoryDB.getInstance().getReservedSlotListTable();
        this.booking = booking;
    }

    public void create(Room room, Person person, LocalDate localDate) {
        boolean isCorrectTime = false;
        while (!isCorrectTime) {
            Slot slot = createSlot(room, person, localDate);
            if (checkDate(slot.getFromLocalDateTime(), slot.getToLocalDateTime())) {
                booking.book(slot);
                isCorrectTime = true;
                System.out.println("The slot has been successfully booked");
            } else {
                System.out.println("The slot is occupied");
            }
        }
    }


    public void read() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void readAll(Long roomId, Person person, LocalDate localDate) {
        Room room = roomCRUD.read(roomId);
        System.out.printf("%-10s  %-20s  %-10s  %-20s  %-20s\n", "№", "Room", "Price", "From", "To");
        for (int initialBookingTime = 9; initialBookingTime < 18; initialBookingTime++) {
            LocalDateTime fromLocalDateTime = LocalDateTime.of(localDate, LocalTime.of(initialBookingTime, 0));
            LocalDateTime toLocalDateTime = LocalDateTime.of(localDate, LocalTime.of(initialBookingTime + 1, 0));
            if (checkDate(fromLocalDateTime, toLocalDateTime)) {
                System.out.printf("%-10s  %-20s  %-10s  %-20s  %-20s\n",
                        room.getRoomId(), room.getRoomName(), room.getPrice(),
                        fromLocalDateTime, toLocalDateTime);
            }
        }
        System.out.println("To book an audience by time, enter 1");
        System.out.println("Back - 0");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                create(room, person, localDate);
        }
    }


    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");

    }


    public void delete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean checkDate(LocalDateTime start1, LocalDateTime end1) {
        for (Slot slot : reservedSlots) {
            if (!(end1.isBefore(slot.getFromLocalDateTime()) || start1.isAfter(slot.getToLocalDateTime()))) {
                return false;
            }
        }
        return true;
    }

    private Slot createSlot(Room room, Person person, LocalDate localDate) {
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
        room.setPrice(room.getPrice() * price);
        LocalDateTime localDateTime1 = LocalDateTime.of(localDate, LocalTime.of(startTime, 0));
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, LocalTime.of(endTime, 0));
        return new Slot(room, person, localDateTime1, localDateTime2);
    }
}
