package com.coworkingservice.fabric;

import com.coworkingservice.entity.Slot;
import com.coworkingservice.memorydb.Create;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.ReadWhereIdAndDate;
import com.coworkingservice.memorydb.ReservedSlotsDelete;

import java.time.LocalDate;
import java.util.List;

public interface ReservedSlotsCRUDAbstractFabric {
    Create<Slot> createCreationMechanism();

    Read<Slot> createReadAllMechanism();

    ReservedSlotsDelete createDeleteMechanism();

    ReadWhereIdAndDate<List<Slot>> createReadWhereIdAndDateMechanism();
}
