package com.coworkingservice.service.booking;

import com.coworkingservice.entity.Slot;
import com.coworkingservice.MemoryDB;
import com.coworkingservice.service.verify.VerifyDate;

import java.util.List;

public class BookingSlot implements Booking<Slot>{
    private final List<Slot> reservedRoomsListTable;
    private final VerifyDate verifyDate;
    public BookingSlot(VerifyDate verifyDate) {
        this.reservedRoomsListTable = MemoryDB.getInstance().getReservedSlotListTable();
        this.verifyDate = new VerifyDate();
    }

    @Override
    public void book(Slot slot) {
        boolean isCorrectTime = false;
        while (!isCorrectTime) {
            if (verifyDate.checkDate(slot.getFromLocalDateTime(), slot.getToLocalDateTime())) {
                reservedRoomsListTable.add(slot);
                isCorrectTime = true;
                System.out.println("The slot has been successfully booked");
            } else {
                System.out.println("The slot is occupied");
            }
        }
    }
}
