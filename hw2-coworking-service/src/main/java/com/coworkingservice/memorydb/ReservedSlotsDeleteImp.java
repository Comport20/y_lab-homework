package com.coworkingservice.memorydb;

import com.coworkingservice.MemoryDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReservedSlotsDeleteImp implements ReservedSlotsDelete {

    @Override
    public boolean delete(int roomId, LocalDateTime localDateTime) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String deleteQuery = "DELETE FROM entity.reserved_slot where from_date = ? AND room_id=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(localDateTime));
                preparedStatement.setInt(2, roomId);
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
