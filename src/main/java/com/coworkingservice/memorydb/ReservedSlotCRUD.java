package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Slot;
import com.coworkingservice.fabric.ReservedSlotCRUDAbstractFabric;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class ReservedSlotCRUD {
    private final ReservedSlotCRUDAbstractFabric reservedSlotsCRUDFabric;

    public ReservedSlotCRUD(ReservedSlotCRUDAbstractFabric reservedSlotsCRUDFabric) {
        this.reservedSlotsCRUDFabric = reservedSlotsCRUDFabric;
    }

    public void create(Slot slot) {
        reservedSlotsCRUDFabric.createCreationMechanism().create(slot);
    }

    public List<Slot> readAll() {
        return reservedSlotsCRUDFabric.createReadAllMechanism().readAll();
    }

    public List<Slot> readWhereIdAndDate(int roomId, LocalDate date) {
        return reservedSlotsCRUDFabric.createReadWhereIdAndDateMechanism().readWhereIdAndDate(roomId, date);
    }

    public boolean delete(int roomId, LocalDateTime localDateTime) {
        return reservedSlotsCRUDFabric.createDeleteMechanism().delete(roomId, localDateTime);
    }
}
