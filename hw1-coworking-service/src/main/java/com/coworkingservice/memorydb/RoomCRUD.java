package com.coworkingservice.memorydb;


import com.coworkingservice.entity.Room;


import com.coworkingservice.fabric.EntityFamilyFabric;


import java.util.Map;


public class RoomCRUD {
    private Map<Long, Room> roomMap;

    private Long roomId;
    private EntityFamilyFabric entityFabric;

    public RoomCRUD(EntityFamilyFabric entityFabric) {
        this.roomId = 1L;
        this.roomMap = MemoryDB.getInstance().getRoomMapTable();
        this.entityFabric = entityFabric;
    }

    public void create() {
        Room newRoom = entityFabric.createRoom(roomId);
        if (newRoom != null) {
            roomMap.put(roomId, newRoom);
            roomId++;
        } else {
            System.out.println("Something went wrong, maybe you made a mistake when entering");
        }
    }


    public Room read(Long roomId) {
        return roomMap.get(roomId);
    }

    public void readAll() {
        System.out.printf("%-10s  %-20s  %-10s\n", "№", "Room", "From");
        for (Map.Entry<Long, Room> entry : roomMap.entrySet()) {
            System.out.printf("%-10s  %-20s  %-10s\n", entry.getKey(),
                    entry.getValue().getRoomName(), entry.getValue().getPrice() + " rub.");
        }
    }


    public void update(Long roomId) {
        if (roomMap.containsKey(roomId)) {
            roomMap.put(roomId, entityFabric.createRoom(roomId));
        } else {
            System.out.println("There is no workspace with this number.");
        }
    }


    public void delete(Long roomId) {
        roomMap.remove(roomId);
    }
}
