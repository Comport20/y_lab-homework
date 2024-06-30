package com.coworkingservice.memorydb;

import com.coworkingservice.MemoryDB;
import com.coworkingservice.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomUpdate implements Update<Room>{
    @Override
    public boolean update(int indicator, Room room) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String updateQuery = "UPDATE entity.room SET auditorium=?, room_name=?, price=? where auditorium=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, indicator);
                preparedStatement.setString(2, room.getRoomName());
                preparedStatement.setDouble(3, room.getPrice());
                preparedStatement.setDouble(4, indicator);
                preparedStatement.executeUpdate();
                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
            return false;
        }
    }
}
