package com.coworkingservice;

import com.coworkingservice.entity.ConferenceRoom;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;
import com.coworkingservice.fabric.EntityReadingFabric;
import com.coworkingservice.fabric.ReservedSlotCRUDAbstractFabricBaseImp;
import com.coworkingservice.memorydb.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ReservedSlotCRUDTest extends PropertiesContainerTest {
    private EntityFamilyReadingFabric entityReadingFabric;
    private ReservedSlotCRUD reservedSlotCRUD;

    @BeforeEach
    void setUp() {
        this.entityReadingFabric = new EntityReadingFabric();
        this.reservedSlotCRUD = new ReservedSlotCRUD(new ReservedSlotCRUDAbstractFabricBaseImp());
    }

    @Test
    @DisplayName("Statement on the creation of a reserved slot")
    public void create() {
        Person person = entityReadingFabric.createPerson(1 ,"Map","Coach","test@test");
        Room room = new ConferenceRoom(2);
        LocalDateTime now = LocalDateTime.of(2024,7,2,12,0);
        LocalDateTime after = now.plusDays(1);
        Slot slot1 = new Slot(room,3600,person,now,after);
        reservedSlotCRUD.create(slot1);
    }

    @Test
    @DisplayName("Statement for reading all data in the reserved_slot table")
    public void readAll() {
        assertInstanceOf(List.class, reservedSlotCRUD.readAll());
    }

    @Test
    @DisplayName("Statement on the deletion of a reserved slot")
    public void delete() {
        LocalDateTime now = LocalDateTime.of(2024,7,2,12,0);
        reservedSlotCRUD.delete(2,now);
    }
}
