package com.coworkingservice.fabric;

import com.coworkingservice.entity.Slot;
import com.coworkingservice.memorydb.Create;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.ReadWhereIdAndDate;
import com.coworkingservice.memorydb.ReservedSlotsDelete;
import com.coworkingservice.memorydb.person.PersonRead;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotReadWhereIdAndDate;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotsCreate;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotsDeleteImp;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotsRead;
import com.coworkingservice.memorydb.room.RoomRead;

import java.util.List;

public class ReservedSlotsCRUDAbstractFabricBaseImp implements ReservedSlotsCRUDAbstractFabric {
    @Override
    public Create<Slot> createCreationMechanism() {
        return new ReservedSlotsCreate();
    }

    @Override
    public Read<Slot> createReadAllMechanism() {
        EntityFamilyReadingFabric entityReadingFabric = new EntityReadingFabric();
        return new ReservedSlotsRead(entityReadingFabric, new RoomRead(entityReadingFabric), new PersonRead(entityReadingFabric));
    }

    @Override
    public ReservedSlotsDelete createDeleteMechanism() {
        return new ReservedSlotsDeleteImp();
    }

    @Override
    public ReadWhereIdAndDate<List<Slot>> createReadWhereIdAndDateMechanism() {
        EntityFamilyReadingFabric entityReadingFabric = new EntityReadingFabric();
        return new ReservedSlotReadWhereIdAndDate(entityReadingFabric, new RoomRead(entityReadingFabric), new PersonRead(entityReadingFabric));
    }
}
