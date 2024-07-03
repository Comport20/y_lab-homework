package com.coworkingservice.service.verify;

import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.memorydb.ReadWhereIdAndDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VerifyDate {
    private final ReadWhereIdAndDate<List<Slot>> readWhereIdAndDate;
    public VerifyDate(ReadWhereIdAndDate<List<Slot>> readWhereIdAndDate) {
        this.readWhereIdAndDate = readWhereIdAndDate;
    }

    public List<Slot> checkDate(Room room, LocalDate localDate) {
        List<Slot> allSlots = createAllSlotsList(room, localDate);
        List<Slot> reservedSlots = readWhereIdAndDate.readWhereIdAndDate(room.getId(),localDate);
        List<Slot> validSlots = new ArrayList<>();
        int startReservedIndex = 0;
        int startAllSlotsIndex;
        for (startAllSlotsIndex = 0; startAllSlotsIndex < allSlots.size() && startReservedIndex < reservedSlots.size(); startAllSlotsIndex++) {
            if (allSlots.get(startAllSlotsIndex).getToLocalDateTime().isBefore(reservedSlots.get(startReservedIndex).getFromLocalDateTime())
                    || allSlots.get(startAllSlotsIndex).getToLocalDateTime().equals(reservedSlots.get(startReservedIndex).getFromLocalDateTime())) {
                validSlots.add(allSlots.get(startAllSlotsIndex));
            } else {
                while (startAllSlotsIndex < allSlots.size() &&
                        !allSlots.get(startAllSlotsIndex).getFromLocalDateTime().equals(reservedSlots.get(startReservedIndex).getToLocalDateTime())){
                    startAllSlotsIndex++;
                }
                startReservedIndex++;
            }
        }
        while(startAllSlotsIndex < allSlots.size()) {
            validSlots.add(allSlots.get(startAllSlotsIndex++));
        }
        return validSlots;
    }
    private List<Slot> createAllSlotsList(Room room, LocalDate localDate) {
        List<Slot> slots = new ArrayList<>();
        for (int initialBookingTime = 9; initialBookingTime < 18; initialBookingTime++) {
            LocalDateTime fromLocalDateTime = LocalDateTime.of(localDate, LocalTime.of(initialBookingTime, 0));
            LocalDateTime toLocalDateTime = LocalDateTime.of(localDate, LocalTime.of(initialBookingTime + 1, 0));
            slots.add(new Slot(room, room.getPrice(),null, fromLocalDateTime, toLocalDateTime));
        }
        return slots;
    }
}
