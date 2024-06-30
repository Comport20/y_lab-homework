package com.coworkingservice.memorydb;

import com.coworkingservice.MemoryDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDelete implements Delete{
    @Override
    public boolean delete(int indicator) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String deleteQuery = "DELETE FROM entity.room where auditorium = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, indicator);
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
