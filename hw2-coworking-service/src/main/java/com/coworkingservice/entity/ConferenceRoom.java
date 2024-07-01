package com.coworkingservice.entity;

public class ConferenceRoom extends Room{
    public ConferenceRoom(int auditorium) {
        super(auditorium, "Conference room", 2500.0);
    }
    public ConferenceRoom(int id, int auditorium) {
        super(id, auditorium,"Conference room", 2500.0);
    }
}
