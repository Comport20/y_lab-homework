package com.coworkingservice.memorydb.reservedslots;

import com.coworkingservice.ConnectionDB;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;
import com.coworkingservice.memorydb.person.PersonRead;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.room.RoomRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ReservedSlotsRead implements Read<Slot> {
    private final EntityFamilyReadingFabric entityFamilyReadingFabric;
    private final Read<Room> roomRead;
    private final Read<Person> personRead;

    public ReservedSlotsRead(EntityFamilyReadingFabric entityFamilyReadingFabric, Read<Room> roomRead, Read<Person> personRead) {
        this.entityFamilyReadingFabric = entityFamilyReadingFabric;
        this.roomRead = roomRead;
        this.personRead = personRead;
    }

    @Override
    public Slot read(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Slot readWhere(int indicator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Slot> readAll() {
        List<Slot> slots = new LinkedList<>();
        try (Connection con = ConnectionDB.getConnection()) {
            con.setAutoCommit(false);
            String readAllQuery = "SELECT * FROM entity.reserved_slot";
            try (PreparedStatement preparedStatement = con.prepareStatement(readAllQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Slot slot = entityFamilyReadingFabric.createSlot(roomRead.read(resultSet.getInt("room_id")),
                            resultSet.getDouble("price"), personRead.read(resultSet.getInt("person_id")),
                            resultSet.getTimestamp("from_date"), resultSet.getTimestamp("to_date"));
                    slots.add(slot);
                }
                con.commit();
                return slots;
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
                e.printStackTrace();
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
            throw new RuntimeException();
        }
    }
}
