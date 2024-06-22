package com.coworkingservice.service.booking;

import com.coworkingservice.entity.Slot;
import com.coworkingservice.memorydb.MemoryDB;

import java.util.List;

public class BookingSlot implements Booking<Slot> {
    private List<Slot> reservedRoomsListTable;

    public BookingSlot() {
        this.reservedRoomsListTable = MemoryDB.getInstance().getReservedSlotListTable();
    }

    @Override
    public void book(Slot slot) {
        reservedRoomsListTable.add(slot);
    }
}
