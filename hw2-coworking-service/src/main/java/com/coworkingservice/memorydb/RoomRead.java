package com.coworkingservice.memorydb;

import com.coworkingservice.MemoryDB;
import com.coworkingservice.entity.Room;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RoomRead implements Read<Room> {
    private final EntityFamilyReadingFabric entityFamilyReadingFabric;

    public RoomRead(EntityFamilyReadingFabric entityFamilyReadingFabric) {
        this.entityFamilyReadingFabric = entityFamilyReadingFabric;
    }

    @Override
    public Room read(int id) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String readQuery = "SELECT * FROM entity.room where id=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(readQuery)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Room room = entityFamilyReadingFabric.createRoom(resultSet.getInt("id"),
                            resultSet.getInt("auditorium"),
                            resultSet.getString("room_name"));
                    con.commit();
                    return room;
                }
                throw new SQLException();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Room readWhere(int indicator) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String readQuery = "SELECT * FROM entity.room where auditorium=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(readQuery)) {
                preparedStatement.setInt(1, indicator);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Room room = entityFamilyReadingFabric.createRoom(resultSet.getInt("id"),
                            resultSet.getInt("auditorium"),
                            resultSet.getString("room_name"));
                    con.commit();
                    return room;
                }
                throw new SQLException();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<Room> readAll() {
        List<Room> rooms = new LinkedList<>();
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String readAllQuery = "SELECT * FROM entity.room";
            try (PreparedStatement preparedStatement = con.prepareStatement(readAllQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Room room = entityFamilyReadingFabric.createRoom(resultSet.getInt("id"),
                            resultSet.getInt("auditorium"),
                            resultSet.getString("room_name"));
                    rooms.add(room);
                }
                con.commit();
                return rooms;
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
