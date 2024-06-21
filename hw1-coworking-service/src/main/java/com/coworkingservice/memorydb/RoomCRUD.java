package com.coworkingservice.memorydb;

import com.coworkingservice.entity.ConferenceRoom;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.WorkplaceRoom;
import com.coworkingservice.service.ScannerSingleton;

import java.util.Map;
import java.util.Scanner;

public class RoomCRUD implements CRUD<Long>{
    private Map<Long,Room> roomMap;
    private final Scanner scanner;
    private Long roomId;
    public RoomCRUD() {
        this.roomId = 0L;
        this.scanner = ScannerSingleton.getInstance().getScanner();
        this.roomMap = MemoryDB.getInstance().getRoomMapTable();
    }
    @Override
    public void create() {
        Room newRoom = createRoom(roomId);
        if(newRoom != null) {
            roomMap.put(roomId, newRoom);
            roomId++;
        }else{
            System.out.println("Что-то пошло не так, возможно вы ошиблись при вводе");
        }
    }

    @Override
    public void read(Long roomId) {
    }

    @Override
    public void readAll() {
        System.out.printf("%-10s  %-20s  %-10s\n", "№", "Комната", "От");
        for(Map.Entry<Long,Room> entry: roomMap.entrySet()){
            System.out.printf("%-10s  %-20s  %-10s\n", entry.getKey(),
                    entry.getValue().getRoomName(), entry.getValue().getPrice() + " руб.");
        }
    }

    @Override
    public void update(Long roomId) {
        if(roomMap.containsKey(roomId)){
            Room room = createRoom(roomId);
            roomMap.put(roomId, room);
        }else{
            System.out.println("Рабочего пространства с даннмы номером не существует.");
        }
    }

    @Override
    public void delete(Long roomId) {
        roomMap.remove(roomId);
    }
    private Room createRoom(Long roomId){
        int roomNumber = scanner.nextInt();
        return switch(roomNumber){
            case 1 -> new WorkplaceRoom(roomId);
            case 2 -> new ConferenceRoom(roomId);
            default -> null;
        };
    }
}
