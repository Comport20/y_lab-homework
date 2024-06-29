package com.coworkingservice;

import com.coworkingservice.entity.ConferenceRoom;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.WorkplaceRoom;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;
import com.coworkingservice.memorydb.RoomCRUD;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomCrudTest {
    private Map<Long, Room> rooms = MemoryDB.getInstance().getRoomMapTable();
    @Mock
    private EntityFamilyFabric entityFamilyFabric;
    @Mock
    private EntityFamilyReadingFabric entityFamilyReadingFabric;
    private RoomCRUD roomCRUD;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStreamCaptor));
        rooms.put(10L, new ConferenceRoom(10));
        this.roomCRUD = new RoomCRUD(entityFamilyFabric, entityFamilyReadingFabric);
    }

    @Test
    public void createRecordWhenNotNullTest() {
        when(entityFamilyFabric.createRoom()).thenReturn(new WorkplaceRoom(1));
        roomCRUD.create();
        Assertions.assertNotNull(rooms.get(1L));
    }

    @Test
    public void createRecordWhenNullTest() {
        when(entityFamilyFabric.createRoom()).thenReturn(null);
        roomCRUD.create();
        Assertions.assertEquals(
                "Something went wrong, maybe you made a mistake when entering",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void updateRecordWhenContainsKeyTest() {
        when(entityFamilyFabric.createRoom()).thenReturn(new WorkplaceRoom(10));
        roomCRUD.update(10);
        Assertions.assertInstanceOf(WorkplaceRoom.class, rooms.get(10));
    }

    public void updateRecordWhenNotContainsKeyTest() {
        roomCRUD.update(1);
        Assertions.assertEquals(
                "There is no workspace with this number.",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void readRecordWhenNullTest() {
        Assertions.assertNull(roomCRUD.read(2));
    }

    @Test
    public void readRecordWhenNotNullTest() {
        Assertions.assertNotNull(roomCRUD.read(10));
    }

    @Test
    public void deleteRecordTest() {
        if (rooms.containsKey(10L)) {
            roomCRUD.delete(10);
            Assertions.assertFalse(rooms.containsKey(10L));
        }
    }

    @After
    public void tearDown() throws Exception {
        rooms.clear();
    }
}
