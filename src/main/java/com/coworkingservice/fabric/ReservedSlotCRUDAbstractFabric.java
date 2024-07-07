package com.coworkingservice.fabric;

import com.coworkingservice.entity.Slot;
import com.coworkingservice.memorydb.Create;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.ReadWhereIdAndDate;
import com.coworkingservice.memorydb.ReservedSlotDelete;

import java.util.List;

public interface ReservedSlotCRUDAbstractFabric {
    Create<Slot> createCreationMechanism();

    Read<Slot> createReadAllMechanism();

    ReservedSlotDelete createDeleteMechanism();

    ReadWhereIdAndDate<List<Slot>> createReadWhereIdAndDateMechanism();
}
