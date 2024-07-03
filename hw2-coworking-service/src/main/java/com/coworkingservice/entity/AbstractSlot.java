package com.coworkingservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@EqualsAndHashCode
public abstract class AbstractSlot {
    protected Room room;
    protected Person person;
    protected LocalDateTime fromLocalDateTime;
    protected LocalDateTime toLocalDateTime;
    protected double price;
    public int getAuditorium(){
        return room.getAuditorium();
    }
    public int getRoomId(){
        return room.getId();
    }
    public int getPersonId(){
        return person.getId();
    }
    public Timestamp getFromTimestamp(){
        return Timestamp.valueOf(fromLocalDateTime);
    }
    public Timestamp getToTimestamp(){
        return Timestamp.valueOf(toLocalDateTime);
    }
    public String getRoomName(){
        return room.getRoomName();
    }
    public String getPersonName(){
        return person.getFirstname() + " " + person.getLastname();
    }
}
