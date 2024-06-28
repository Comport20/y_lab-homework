package com.coworkingservice.service.verify;

import com.coworkingservice.entity.Slot;
import com.coworkingservice.memorydb.MemoryDB;

import java.time.LocalDateTime;
import java.util.List;

public class VerifyDate {
    private final List<Slot> reservedSlots;
    public VerifyDate(){
        reservedSlots = MemoryDB.getInstance().getReservedSlotListTable();
    }
    public boolean checkDate(LocalDateTime start1, LocalDateTime end1) {
        for (Slot slot : reservedSlots) {
            if (!(end1.isBefore(slot.getFromLocalDateTime()) || start1.isAfter(slot.getToLocalDateTime()))
                    && !end1.isEqual(slot.getFromLocalDateTime())) {
                return false;
            }
        }
        return true;
    }
}
