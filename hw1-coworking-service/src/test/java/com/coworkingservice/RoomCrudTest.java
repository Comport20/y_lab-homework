package com.coworkingservice;

import com.coworkingservice.entity.ConferenceRoom;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.WorkplaceRoom;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.memorydb.MemoryDB;
import com.coworkingservice.memorydb.RoomCRUD;
import org.junit.After;
import org.junit.Assert;
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
    private RoomCRUD roomCRUD;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStreamCaptor));
        rooms.put(10L,new ConferenceRoom(10L));
        this.roomCRUD = new RoomCRUD(entityFamilyFabric);
    }
    @Test
    public void createRecordWhenNotNullTest(){
        when(entityFamilyFabric.createRoom(1L)).thenReturn(new WorkplaceRoom(1L));
        roomCRUD.create();
        Assertions.assertNotNull(rooms.get(1L));
    }
    @Test
    public void createRecordWhenNullTest(){
        when(entityFamilyFabric.createRoom(1L)).thenReturn(null);
        roomCRUD.create();
        Assertions.assertEquals(
                "Something went wrong, maybe you made a mistake when entering",
                outputStreamCaptor.toString().trim());
    }
    @Test
    public void updateRecordWhenContainsKeyTest(){
        when(entityFamilyFabric.createRoom(10L)).thenReturn(new WorkplaceRoom(10L));
        roomCRUD.update(10L);
        Assertions.assertInstanceOf(WorkplaceRoom.class, rooms.get(10L));
    }
    public void updateRecordWhenNotContainsKeyTest(){
        roomCRUD.update(1L);
        Assertions.assertEquals(
                "There is no workspace with this number.",
                outputStreamCaptor.toString().trim());
    }
    @Test
    public void readRecordWhenNullTest(){
        Assertions.assertNull(roomCRUD.read(2L));
    }
    @Test
    public void readRecordWhenNotNullTest(){
        Assertions.assertNotNull(roomCRUD.read(10L));
    }
    @Test
    public void deleteRecordTest(){
        if(rooms.containsKey(10L)){
            roomCRUD.delete(10L);
            Assertions.assertFalse(rooms.containsKey(10L));
        }
    }
    @After
    public void tearDown() throws Exception {
        rooms.clear();
    }
}
