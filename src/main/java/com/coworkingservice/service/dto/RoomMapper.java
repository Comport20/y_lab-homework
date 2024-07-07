package com.coworkingservice.service.dto;

import com.coworkingservice.entity.ConferenceRoom;
import com.coworkingservice.entity.Room;
import com.coworkingservice.entity.WorkplaceRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    RoomDTO toRoomDTO(Room room);
    @Mapping(target = "id", ignore = true)
    Room toRoom(RoomDTO roomDTO);
    List<RoomDTO> toRoomDTO(List<Room> rooms);
    @Mapping(target = "id", ignore = true)
    List<Room> toRoom(List<RoomDTO> roomDTO);
    @ObjectFactory
    default Room convertRoomDTOToRoom(RoomDTO roomDTO) {
        switch (roomDTO.roomName()){
            case "Conference room"-> new ConferenceRoom(roomDTO.auditorium());
            case "Workplace"-> new WorkplaceRoom(roomDTO.auditorium());
        }
        throw new IllegalArgumentException("Invalid room name");
    }
}
