package com.coworkingservice.service.verify;

import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.memorydb.ReservedSlotCRUD;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.service.ScannerSingleton;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FreeSlotsCheck {
    private final RoomCRUD roomCRUD;
    private final Scanner scanner;
    private final VerifyDate verifyDate;
    private final ReservedSlotCRUD reservedSlotCRUD;
    private final EntityFamilyFabric entityFamilyFabric;
    public FreeSlotsCheck(EntityFamilyFabric entityFamilyFabric, RoomCRUD roomCRUD, ReservedSlotCRUD reservedSlotCRUD, VerifyDate verifyDate) {
        this.roomCRUD = roomCRUD;
        this.scanner = ScannerSingleton.getInstance().getScanner();
        this.verifyDate = verifyDate;
        this.reservedSlotCRUD = reservedSlotCRUD;
        this.entityFamilyFabric = entityFamilyFabric;
    }

    public void readAll(int audience, Person person, LocalDate localDate) {
        Room room = roomCRUD.readWhere(audience);
        List<Slot> freeSlots = verifyDate.checkDate(room,localDate);
        displaySlots(freeSlots);
        System.out.println("To book an audience by time, enter 1");
        System.out.println("Back - 0");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                reservedSlotCRUD.create(entityFamilyFabric.createSlot(room, person, localDate));
        }
    }


    private void displaySlots(List<Slot> slots) {
        System.out.printf("%-10s  %-20s  %-10s  %-20s  %-20s\n", "№", "Room", "Price", "From", "To");
        for (Slot slot: slots) {
            System.out.printf("%-10s  %-20s  %-10s  %-20s  %-20s\n",
                    slot.getAuditorium(), slot.getRoomName(), slot.getPrice(),
                    slot.getFromLocalDateTime(), slot.getToLocalDateTime());
        }
    }
}
