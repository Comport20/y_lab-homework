package com.coworkingservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public abstract class AbstractSlot {
    protected Room room;
    protected Person person;
    protected LocalDateTime fromLocalDateTime;
    protected LocalDateTime toLocalDateTime;

}
