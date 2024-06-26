package com.coworkingservice.memorydb;


import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.service.ScannerSingleton;
import com.coworkingservice.service.verify.VerifyDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class FreeSlotsCRUD {
    private final RoomCRUD roomCRUD;
    private final Scanner scanner;
    private VerifyDate verifyDate;
    private final ReservedSlotsCRUD reservedSlotsCRUD;

    public FreeSlotsCRUD(RoomCRUD roomCRUD, ReservedSlotsCRUD reservedSlotsCRUD, VerifyDate verifyDate) {
        this.roomCRUD = roomCRUD;
        this.scanner = ScannerSingleton.getInstance().getScanner();
        this.verifyDate = verifyDate;
        this.reservedSlotsCRUD = reservedSlotsCRUD;
    }

    public void create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void read() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void readAll(Long roomId, Person person, LocalDate localDate) {
        Room room = roomCRUD.read(roomId);
        System.out.printf("%-10s  %-20s  %-10s  %-20s  %-20s\n", "№", "Room", "Price", "From", "To");
        displaySlots(room, localDate);
        System.out.println("To book an audience by time, enter 1");
        System.out.println("Back - 0");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                reservedSlotsCRUD.create(room, person, localDate);
        }
    }


    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");

    }


    public void delete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void displaySlots(Room room, LocalDate localDate) {
        for (int initialBookingTime = 9; initialBookingTime < 18; initialBookingTime++) {
            LocalDateTime fromLocalDateTime = LocalDateTime.of(localDate, LocalTime.of(initialBookingTime, 0));
            LocalDateTime toLocalDateTime = LocalDateTime.of(localDate, LocalTime.of(initialBookingTime + 1, 0));
            if (verifyDate.checkDate(fromLocalDateTime, toLocalDateTime)) {
                System.out.printf("%-10s  %-20s  %-10s  %-20s  %-20s\n",
                        room.getRoomId(), room.getRoomName(), room.getPrice(),
                        fromLocalDateTime, toLocalDateTime);
            }
        }
    }
}
