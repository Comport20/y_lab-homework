package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Slot;

import java.time.LocalDateTime;
import java.util.List;


public class ReservedSlotsCRUD {
    private final Read<Slot> reservedSlotsRead;
    private final Create<Slot> reservedSlotsCreate;
    private final ReservedSlotsDelete reservedSlotsDelete;
    public ReservedSlotsCRUD(Create<Slot> reservedSlotsCreate, Read<Slot> reservedSlotsRead,ReservedSlotsDelete reservedSlotsDelete) {
        this.reservedSlotsCreate = reservedSlotsCreate;
        this.reservedSlotsRead = reservedSlotsRead;
        this.reservedSlotsDelete = reservedSlotsDelete;
    }

    public void create(Slot slot) {
        reservedSlotsCreate.create(slot);
    }

    public List<Slot> readAll() {
        return reservedSlotsRead.readAll();
    }

    public boolean delete(int roomId, LocalDateTime localDateTime) {
        return reservedSlotsDelete.delete(roomId, localDateTime);
    }
}
