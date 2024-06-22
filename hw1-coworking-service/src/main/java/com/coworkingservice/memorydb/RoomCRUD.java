package com.coworkingservice.memorydb;

import com.coworkingservice.entity.ConferenceRoom;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.WorkplaceRoom;
import com.coworkingservice.service.ScannerSingleton;

import java.util.Map;
import java.util.Scanner;

public class RoomCRUD {
    private Map<Long, Room> roomMap;
    private final Scanner scanner;
    private Long roomId;

    public RoomCRUD() {
        this.roomId = 0L;
        this.scanner = ScannerSingleton.getInstance().getScanner();
        this.roomMap = MemoryDB.getInstance().getRoomMapTable();
    }

    public void create() {
        Room newRoom = createRoom(roomId);
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
            Room room = createRoom(roomId);
            roomMap.put(roomId, room);
        } else {
            System.out.println("There is no workspace with this number.");
        }
    }


    public void delete(Long roomId) {
        roomMap.remove(roomId);
    }

    private Room createRoom(Long roomId) {
        System.out.println("To create a workplace, press 1");
        System.out.println("To create a conference room, press 2");
        return switch (scanner.nextInt()) {
            case 1 -> new WorkplaceRoom(roomId);
            case 2 -> new ConferenceRoom(roomId);
            default -> null;
        };
    }
}
