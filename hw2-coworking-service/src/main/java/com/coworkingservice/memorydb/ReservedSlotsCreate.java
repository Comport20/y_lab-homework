package com.coworkingservice.memorydb;

import com.coworkingservice.MemoryDB;
import com.coworkingservice.entity.Slot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservedSlotsCreate implements Create<Slot>{
    public ReservedSlotsCreate(){
    }
    @Override
    public void create(Slot slot) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String insertQuery = "INSERT INTO entity.room (room_id, price, person_id, from_date, to_date) " +
                    "VALUES(?,?,?,?,?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, slot.getRoomId());
                preparedStatement.setDouble(2, slot.getPrice());
                preparedStatement.setInt(3, slot.getPersonId());
                preparedStatement.setTimestamp(4, slot.getFromTimestamp());
                preparedStatement.setTimestamp(5, slot.getToTimestamp());
                preparedStatement.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
        }
    }
}
