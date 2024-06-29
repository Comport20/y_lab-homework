package com.coworkingservice;

import com.coworkingservice.entity.*;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.memorydb.ReservedSlotsCRUD;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.service.booking.Booking;
import com.coworkingservice.service.booking.BookingSlot;
import com.coworkingservice.service.verify.VerifyDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservedSlotsCrudTest {
    private final List<Slot> reservedSlotListTable = MemoryDB.getInstance().getReservedSlotListTable();
    @Mock
    private RoomCRUD roomCrud;
    private Booking<Slot> booking;
    @Mock
    private EntityFamilyFabric entityFabric;
    private ReservedSlotsCRUD reservedSlotsCRUD;
    @Mock
    private VerifyDate verifyDate;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStreamCaptor));
        booking = new BookingSlot(verifyDate);
        this.reservedSlotsCRUD = new ReservedSlotsCRUD(roomCrud, booking, entityFabric);
    }

    @Test
    public void createBookingRecordTest() {
        Room room = Mockito.mock(Room.class);
        Person person = Mockito.mock(Person.class);
        LocalDate localDate = Mockito.mock(LocalDate.class);
        when(entityFabric.createSlot(room, person, localDate)).
                thenReturn(new Slot(room, 5000.0, person,
                        LocalDateTime.parse("2024-06-25T12:00"),
                        LocalDateTime.parse("2024-06-25T16:00")));
        reservedSlotsCRUD.create(room, person, localDate);
        Assertions.assertEquals("The slot has been successfully booked", outputStreamCaptor.toString().trim());
    }

    @Test
    public void updateRecordTest() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> reservedSlotsCRUD.update());
    }

    @Test
    public void readRecordTest() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> reservedSlotsCRUD.read());
    }

    @Test
    public void deleteRecordIsDeleteTest() {
        Room room = new WorkplaceRoom(1);
        Person person = Mockito.mock(Person.class);
        LocalDateTime localDateTime = LocalDateTime.parse("2024-06-25T10:00");
        Slot spySlot = spy(new Slot(room, 5000.0, person, localDateTime, localDateTime));
        when(roomCrud.read(anyInt())).thenReturn(room);
        reservedSlotListTable.add(spySlot);
        reservedSlotsCRUD.delete(1L, localDateTime);
        Assertions.assertEquals("The booking has been cancelled successfully", outputStreamCaptor.toString().trim());
    }

    @Test
    public void deleteRecordNotDeleteTest() {
        Room room = new WorkplaceRoom(1);
        Person person = Mockito.mock(Person.class);
        LocalDateTime localDateTime = LocalDateTime.parse("2024-06-25T10:00");
        Slot spySlot = spy(new Slot(room, 5000.0, person, localDateTime, localDateTime));
        when(roomCrud.read(anyInt())).thenReturn(null);
        reservedSlotListTable.add(spySlot);
        reservedSlotsCRUD.delete(2L, localDateTime);
        Assertions.assertEquals("This booking was not found", outputStreamCaptor.toString().trim());
    }
}
