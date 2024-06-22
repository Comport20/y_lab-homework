package com.coworkingservice.entity;

import java.time.LocalDateTime;

public abstract class AbstractSlot {
    protected Room room;
    protected Person person;
    protected LocalDateTime fromLocalDateTime;
    protected LocalDateTime toLocalDateTime;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getFromLocalDateTime() {
        return fromLocalDateTime;
    }

    public void setFromLocalDateTime(LocalDateTime fromtLocalDateTime) {
        this.fromLocalDateTime = fromtLocalDateTime;
    }

    public LocalDateTime getToLocalDateTime() {
        return toLocalDateTime;
    }

    public void setToLocalDateTime(LocalDateTime toLocalDateTime) {
        this.toLocalDateTime = toLocalDateTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
