package com.coworkingservice.service.verify;

import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.memorydb.ReservedSlotsCRUD;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.service.ScannerSingleton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class FreeSlotsCheck {
    private final RoomCRUD roomCRUD;
    private final Scanner scanner;
    private final VerifyDate verifyDate;
    private final ReservedSlotsCRUD reservedSlotsCRUD;
    private final EntityFamilyFabric entityFamilyFabric;
    public FreeSlotsCheck(EntityFamilyFabric entityFamilyFabric,RoomCRUD roomCRUD, ReservedSlotsCRUD reservedSlotsCRUD, VerifyDate verifyDate) {
        this.roomCRUD = roomCRUD;
        this.scanner = ScannerSingleton.getInstance().getScanner();
        this.verifyDate = verifyDate;
        this.reservedSlotsCRUD = reservedSlotsCRUD;
        this.entityFamilyFabric = entityFamilyFabric;
    }

    public void readAll(int audience, Person person, LocalDate localDate) {
        Room room = roomCRUD.readWhere(audience);
        System.out.printf("%-10s  %-20s  %-10s  %-20s  %-20s\n", "№", "Room", "Price", "From", "To");
        displaySlots(room, localDate);
        System.out.println("To book an audience by time, enter 1");
        System.out.println("Back - 0");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                reservedSlotsCRUD.create(entityFamilyFabric.createSlot(room, person, localDate));
        }
    }
    private void displaySlots(Room room, LocalDate localDate) {
        for (int initialBookingTime = 9; initialBookingTime < 18; initialBookingTime++) {
            LocalDateTime fromLocalDateTime = LocalDateTime.of(localDate, LocalTime.of(initialBookingTime, 0));
            LocalDateTime toLocalDateTime = LocalDateTime.of(localDate, LocalTime.of(initialBookingTime + 1, 0));
            if (verifyDate.checkDate(fromLocalDateTime, toLocalDateTime)) {
                System.out.printf("%-10s  %-20s  %-10s  %-20s  %-20s\n",
                        room.getAuditorium(), room.getRoomName(), room.getPrice(),
                        fromLocalDateTime, toLocalDateTime);
            }
        }
    }
}
