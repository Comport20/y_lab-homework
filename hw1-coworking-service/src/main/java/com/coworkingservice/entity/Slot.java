package com.coworkingservice.entity;

import java.time.LocalDateTime;

public class Slot extends AbstractSlot{
    public Slot(Room room, Person person, LocalDateTime fromtLocalDateTime, LocalDateTime toLocalDateTime) {
        this.room = room;
        this.person = person;
        this.fromLocalDateTime = fromtLocalDateTime;
        this.toLocalDateTime = toLocalDateTime;
    }
}
