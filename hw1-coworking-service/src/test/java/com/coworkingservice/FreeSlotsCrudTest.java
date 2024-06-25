package com.coworkingservice;




import com.coworkingservice.memorydb.FreeSlotsCRUD;

import com.coworkingservice.memorydb.ReservedSlotsCRUD;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.service.verify.VerifyDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class FreeSlotsCrudTest {
    @Mock
    private RoomCRUD roomCRUD;
    @Mock
    private VerifyDate verifyDate;
    @Mock
    private ReservedSlotsCRUD reservedSlotsCRUD;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private FreeSlotsCRUD freeSlotsCRUD;
    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStreamCaptor));
        freeSlotsCRUD = new FreeSlotsCRUD(roomCRUD,reservedSlotsCRUD,verifyDate);
    }
    @Test
    public void createRecordTest(){
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {freeSlotsCRUD.create();});
    }
    @Test
    public void updateRecordTest(){
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {freeSlotsCRUD.update();});
    }
    @Test
    public void readRecordTest(){
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {freeSlotsCRUD.read();});
    }
    @Test
    public void deleteRecordTest(){
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {freeSlotsCRUD.delete();});
    }
}
