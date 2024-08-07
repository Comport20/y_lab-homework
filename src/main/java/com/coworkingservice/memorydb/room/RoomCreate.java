package com.coworkingservice.memorydb.room;

import com.coworkingservice.ConnectionDB;
import com.coworkingservice.entity.Room;
import com.coworkingservice.memorydb.Create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomCreate implements Create<Room> {
    @Override
    public void create(Room room) {
        try (Connection con = ConnectionDB.getConnection()) {
            con.setAutoCommit(false);
            String insertQuery = "INSERT INTO entity.room (auditorium, room_name,price) VALUES(?,?,?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, room.getAuditorium());
                preparedStatement.setString(2, room.getRoomName());
                preparedStatement.setDouble(3, room.getPrice());
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
