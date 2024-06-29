package com.coworkingservice.memorydb;


import com.coworkingservice.MemoryDB;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.service.booking.Booking;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class ReservedSlotsCRUD {
    private final List<Slot> reservedSlotListTable;
    private final RoomCRUD roomCrud;
    private final Booking<Slot> booking;
    private final EntityFamilyFabric entityFabric;

    public ReservedSlotsCRUD(RoomCRUD roomCrud, Booking<Slot> booking, EntityFamilyFabric entityFabric) {
        this.reservedSlotListTable = MemoryDB.getInstance().getReservedSlotListTable();
        this.roomCrud = roomCrud;
        this.booking = booking;
        this.entityFabric = entityFabric;
    }

    public void create(Room room, Person person, LocalDate localDate) {
        booking.book(entityFabric.createSlot(room, person, localDate));
    }

    public void read() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void readAll() {
        System.out.printf("%-10s  %-20s  %-20s  %-40s  %-20s  %-20s\n", "№", "Room", "Price", "Tenant", "From", "To");
        for (Slot reservedSlot : reservedSlotListTable) {
            System.out.printf("%-10s  %-20s  %-20s  %-40s  %-20s  %-20s\n",
                    reservedSlot.getRoom().getAuditorium(), reservedSlot.getRoom().getRoomName(),
                    reservedSlot.getPrice() + " rub.",
                    reservedSlot.getPerson().getFirstname() + " " +
                            reservedSlot.getPerson().getLastname(),
                    reservedSlot.getFromLocalDateTime().toString(),
                    reservedSlot.getToLocalDateTime().toString());
        }
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(Long roomId, LocalDateTime localDateTime) {
        //Временная мера
        Room room = null;
        if (reservedSlotListTable.removeIf
                (reservedSlot ->
                        reservedSlot.getRoom().equals(room) && reservedSlot.getFromLocalDateTime().equals(localDateTime))) {
            System.out.println("The booking has been cancelled successfully");
        } else {
            System.out.println("This booking was not found");
        }
    }
}
