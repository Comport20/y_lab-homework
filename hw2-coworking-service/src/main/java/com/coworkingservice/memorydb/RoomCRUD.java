package com.coworkingservice.memorydb;


import com.coworkingservice.MemoryDB;
import com.coworkingservice.entity.Room;


import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class RoomCRUD {
    private final Map<Long, Room> roomMap;
    private final EntityFamilyFabric entityFabric;
    private final EntityFamilyReadingFabric entityReadingFabric;

    public RoomCRUD(EntityFamilyFabric entityFabric, EntityFamilyReadingFabric entityReadingFabric) {
        this.roomMap = MemoryDB.getInstance().getRoomMapTable();
        this.entityFabric = entityFabric;
        this.entityReadingFabric = entityReadingFabric;
    }

    public void create() {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            Room newRoom = entityFabric.createRoom();
            String insertQuery = "INSERT INTO entity.room (auditorium, room_name,price) VALUES(?,?,?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, newRoom.getAuditorium());
                preparedStatement.setString(2, newRoom.getRoomName());
                preparedStatement.setDouble(3, newRoom.getPrice());
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


    public Room read(int auditorium) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String readQuery = "SELECT * FROM entity.room where auditorium=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(readQuery)) {
                preparedStatement.setInt(1, auditorium);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Room room = entityReadingFabric.createRoom(resultSet.getInt("auditorium"),
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

    public List<Room> readAll() {
        List<Room> rooms = new LinkedList<>();
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String deleteQuery = "SELECT * FROM entity.room";
            try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Room room = entityReadingFabric.createRoom(resultSet.getInt("auditorium"),
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


    public boolean update(int auditorium) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            Room newRoom = entityFabric.createRoom();
            String deleteQuery = "UPDATE entity.room SET auditorium=?, room_name=?, price=? where auditorium=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, auditorium);
                preparedStatement.setString(2, newRoom.getRoomName());
                preparedStatement.setDouble(3, newRoom.getPrice());
                preparedStatement.setDouble(4, auditorium);
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


    public boolean delete(int auditorium) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String deleteQuery = "DELETE FROM entity.room where auditorium = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, auditorium);
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
