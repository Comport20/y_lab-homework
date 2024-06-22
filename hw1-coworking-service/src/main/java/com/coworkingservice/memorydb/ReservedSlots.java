package com.coworkingservice.memorydb;


import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;


import java.time.LocalDateTime;
import java.util.List;


public class ReservedSlots {
    private final List<Slot> reservedSlotListTable;
    private final RoomCRUD roomCrud;

    public ReservedSlots(RoomCRUD roomCrud) {
        this.reservedSlotListTable = MemoryDB.getInstance().getReservedSlotListTable();
        this.roomCrud = roomCrud;
    }

    public void create() {
    }

    public void read() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void readAll() {
        System.out.printf("%-10s  %-20s  %-20s  %-40s  %-20s  %-20s\n", "№", "Room", "Price", "Tenant", "From", "To");
        for (Slot reservedSlot : reservedSlotListTable) {
            System.out.printf("%-10s  %-20s  %-20s  %-40s  %-20s  %-20s\n",
                    reservedSlot.getRoom().getRoomId(), reservedSlot.getRoom().getRoomName(),
                    reservedSlot.getRoom().getPrice() + " rub.",
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
        Room room = roomCrud.read(roomId);
        if(reservedSlotListTable.removeIf
                (reservedSlot ->
                        reservedSlot.getRoom().equals(room) && reservedSlot.getFromLocalDateTime().equals(localDateTime))){
            System.out.println("Бронирование успешно отменено");
        }else{
            System.out.println("Данное бронирование не найдено");
        }
    }
}
