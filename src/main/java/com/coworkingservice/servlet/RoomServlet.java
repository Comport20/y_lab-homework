package com.coworkingservice.servlet;

import com.coworkingservice.entity.Room;
import com.coworkingservice.fabric.RoomCRUDAbstractFabricBaseImp;
import com.coworkingservice.memorydb.RoomCRUD;
import com.coworkingservice.service.dto.RoomDTO;
import com.coworkingservice.service.dto.RoomMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/room")
public class RoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomCRUD roomCRUD = new RoomCRUD(new RoomCRUDAbstractFabricBaseImp());
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        String findByAuditorium = req.getParameter("id");
        try (var respPrintWrite = resp.getOutputStream()) {
            if (findByAuditorium != null) {
                int auditoriumId = Integer.parseInt(findByAuditorium);
                Room room = roomCRUD.readWhere(auditoriumId);
                RoomDTO roomDTO = RoomMapper.INSTANCE.toRoomDTO(room);
                byte[] json = objectMapper.writeValueAsBytes(roomDTO);
                respPrintWrite.write(json);
            } else {
                List<Room> rooms = roomCRUD.readAll();
                List<RoomDTO> roomDTOS = RoomMapper.INSTANCE.toRoomDTO(rooms);
                byte[] json = objectMapper.writeValueAsBytes(roomDTOS);
                respPrintWrite.write(json);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomCRUD roomCRUD = new RoomCRUD(new RoomCRUDAbstractFabricBaseImp());
        try {
            RoomDTO roomDTO = transferToDTO(req);
            Room room = RoomMapper.INSTANCE.toRoom(roomDTO);
            roomCRUD.create(room);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomCRUD roomCRUD = new RoomCRUD(new RoomCRUDAbstractFabricBaseImp());
        try {
            int queryStringId = Integer.parseInt(req.getParameter("id"));
            roomCRUD.delete(queryStringId);
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomCRUD roomCRUD = new RoomCRUD(new RoomCRUDAbstractFabricBaseImp());
        try {
            int queryStringId = Integer.parseInt(req.getParameter("id"));
            RoomDTO roomDTO = transferToDTO(req);
            Room room = RoomMapper.INSTANCE.toRoom(roomDTO);
            roomCRUD.update(queryStringId, room);
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private RoomDTO transferToDTO(HttpServletRequest req) {
        int id = -1;
        int auditorium = Integer.parseInt(req.getParameter("auditorium"));
        String roomName = req.getParameter("roomName");
        double price = Double.parseDouble(req.getParameter("price"));
        return new RoomDTO(id, auditorium, roomName, price);
    }
}
