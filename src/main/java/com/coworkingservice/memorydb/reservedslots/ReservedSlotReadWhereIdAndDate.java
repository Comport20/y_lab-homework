package com.coworkingservice.memorydb.reservedslots;

import com.coworkingservice.ConnectionDB;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.Slot;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.ReadWhereIdAndDate;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservedSlotReadWhereIdAndDate implements ReadWhereIdAndDate<List<Slot>> {
    private final EntityFamilyReadingFabric entityFamilyReadingFabric;
    private final Read<Room> roomRead;
    private final Read<Person> personRead;
    public ReservedSlotReadWhereIdAndDate(EntityFamilyReadingFabric entityFamilyReadingFabric, Read<Room> roomRead, Read<Person> personRead) {
        this.entityFamilyReadingFabric = entityFamilyReadingFabric;
        this.roomRead = roomRead;
        this.personRead = personRead;
    }
    @Override
    public List<Slot> readWhereIdAndDate(int id, LocalDate date) {
        List<Slot> slots = new ArrayList<>();
        try (Connection con = ConnectionDB.getConnection()) {
            con.setAutoCommit(false);
            String readAllQuery = "SELECT * FROM entity.reserved_slot where room_id=? AND from_date::date=?" +
                    " ORDER BY from_date";
            try (PreparedStatement preparedStatement = con.prepareStatement(readAllQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setDate(2, Date.valueOf(date));
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
