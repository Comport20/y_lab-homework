package com.coworkingservice.fabric;

import com.coworkingservice.entity.Room;
import com.coworkingservice.memorydb.Create;
import com.coworkingservice.memorydb.Delete;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.Update;
import com.coworkingservice.memorydb.room.RoomCreate;
import com.coworkingservice.memorydb.room.RoomDelete;
import com.coworkingservice.memorydb.room.RoomRead;
import com.coworkingservice.memorydb.room.RoomUpdate;

public class RoomCRUDAbstractFabricBaseImp implements RoomCRUDAbstractFabric{
    @Override
    public Create<Room> createCreationMechanism() {
        return new RoomCreate();
    }

    @Override
    public Read<Room> createReadWhereMechanism() {
        return new RoomRead(new EntityReadingFabric());
    }

    @Override
    public Read<Room> createReadAllMechanism() {
        return new RoomRead(new EntityReadingFabric());
    }

    @Override
    public Update<Room> createUpdateMechanism() {
        return new RoomUpdate();
    }

    @Override
    public Delete createDeleteMechanism() {
        return new RoomDelete();
    }
}
