package com.coworkingservice.entity;

import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = false)
public class Slot extends AbstractSlot{
    public Slot(Room room,double price, Person person, LocalDateTime fromtLocalDateTime, LocalDateTime toLocalDateTime) {
        this.room = room;
        this.price = price;
        this.person = person;
        this.fromLocalDateTime = fromtLocalDateTime;
        this.toLocalDateTime = toLocalDateTime;
    }
}
