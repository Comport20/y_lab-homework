package com.coworkingservice.fabric;

import com.coworkingservice.entity.Slot;
import com.coworkingservice.memorydb.Create;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.ReadWhereIdAndDate;
import com.coworkingservice.memorydb.ReservedSlotDelete;
import com.coworkingservice.memorydb.person.PersonRead;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotReadWhereIdAndDate;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotCreate;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotDeleteImp;
import com.coworkingservice.memorydb.reservedslots.ReservedSlotRead;
import com.coworkingservice.memorydb.room.RoomRead;

import java.util.List;

public class ReservedSlotCRUDAbstractFabricBaseImp implements ReservedSlotCRUDAbstractFabric {
    @Override
    public Create<Slot> createCreationMechanism() {
        return new ReservedSlotCreate();
    }

    @Override
    public Read<Slot> createReadAllMechanism() {
        EntityFamilyReadingFabric entityReadingFabric = new EntityReadingFabric();
        return new ReservedSlotRead(entityReadingFabric, new RoomRead(entityReadingFabric), new PersonRead(entityReadingFabric));
    }

    @Override
    public ReservedSlotDelete createDeleteMechanism() {
        return new ReservedSlotDeleteImp();
    }

    @Override
    public ReadWhereIdAndDate<List<Slot>> createReadWhereIdAndDateMechanism() {
        EntityFamilyReadingFabric entityReadingFabric = new EntityReadingFabric();
        return new ReservedSlotReadWhereIdAndDate(entityReadingFabric, new RoomRead(entityReadingFabric), new PersonRead(entityReadingFabric));
    }
}
