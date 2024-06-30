package com.coworkingservice.entity;

public class WorkplaceRoom extends Room{
    public WorkplaceRoom(int auditorium) {
        super(auditorium,"Workplace", 1800.0);
    }
    public WorkplaceRoom(int id, int auditorium) {
        super(id, auditorium,"Workplace", 1800.0);
    }
}
