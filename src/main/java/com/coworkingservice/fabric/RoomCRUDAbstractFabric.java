package com.coworkingservice.fabric;

import com.coworkingservice.entity.Room;
import com.coworkingservice.memorydb.Create;
import com.coworkingservice.memorydb.Delete;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.Update;

public interface RoomCRUDAbstractFabric {
    Create<Room> createCreationMechanism();

    Read<Room> createReadWhereMechanism();

    Read<Room> createReadAllMechanism();

    Update<Room> createUpdateMechanism();

    Delete createDeleteMechanism();
}
