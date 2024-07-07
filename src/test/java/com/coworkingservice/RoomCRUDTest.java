package com.coworkingservice;

import com.coworkingservice.entity.ConferenceRoom;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.WorkplaceRoom;
import com.coworkingservice.fabric.EntityReadingFabric;
import com.coworkingservice.fabric.RoomCRUDAbstractFabricBaseImp;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.memorydb.room.RoomCreate;
import com.coworkingservice.memorydb.room.RoomDelete;
import com.coworkingservice.memorydb.room.RoomRead;
import com.coworkingservice.memorydb.room.RoomUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomCRUDTest extends PropertiesContainerTest {
    private RoomCRUD roomCRUD;

    @BeforeEach
    void setUp() {
        roomCRUD = new RoomCRUD(new RoomCRUDAbstractFabricBaseImp());
    }

    @Test
    @DisplayName("Statement on the creation of a room")
    void create() {
        int auditorium = 11;
        Room room = new WorkplaceRoom(auditorium);
        int originalSize = roomCRUD.readAll().size();
        roomCRUD.create(room);
        List<Room> rooms = roomCRUD.readAll();
        Room readRoom = roomCRUD.readWhere(auditorium);
        assertEquals(originalSize + 1, rooms.size());
        assertEquals(auditorium, readRoom.getAuditorium());
    }

    @Test
    @DisplayName("Statement for reading the room by audience")
    void readWhere() {
        int auditorium = 100;
        Room room = new ConferenceRoom(auditorium);
        roomCRUD.create(room);
        Room readRoom = roomCRUD.readWhere(auditorium);
        assertEquals(room.getAuditorium(), readRoom.getAuditorium());
        assertEquals(room.getRoomName(), readRoom.getRoomName());
        assertEquals(room.getPrice(), readRoom.getPrice());
        readRoom = roomCRUD.readWhere(1);
        assertEquals(1, readRoom.getAuditorium());
    }

    @Test
    @DisplayName("Statement for reading all data in the room table")
    void readAll() {
        int originalSize = roomCRUD.readAll().size();
        roomCRUD.create(new ConferenceRoom(101));
        roomCRUD.create(new WorkplaceRoom(102));
        roomCRUD.create(new ConferenceRoom(103));
        roomCRUD.create(new WorkplaceRoom(104));
        List<Room> rooms = roomCRUD.readAll();
        assertEquals(originalSize + 4, rooms.size());
    }

    @Test
    @DisplayName("Statement for updating a room")
    void update() {
        int auditorium = 105;
        Room room = new WorkplaceRoom(auditorium);
        roomCRUD.create(room);
        room = roomCRUD.readWhere(auditorium);
        assertEquals("Workplace", room.getRoomName());
        Room updateRoom = new ConferenceRoom(auditorium);
        roomCRUD.update(auditorium, updateRoom);
        updateRoom = roomCRUD.readWhere(auditorium);
        assertEquals("Conference room", updateRoom.getRoomName());
    }

    @Test
    @DisplayName("Statement on the deletion of a room by audience")
    void delete() {
        int auditorium = 110;
        int originalSize = roomCRUD.readAll().size();
        Room room = new WorkplaceRoom(auditorium);
        roomCRUD.create(room);
        int currentSize = roomCRUD.readAll().size();
        assertEquals(originalSize + 1, currentSize);
        roomCRUD.delete(auditorium);
        currentSize = roomCRUD.readAll().size();
        assertEquals(originalSize, currentSize);
    }
}
