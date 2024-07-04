package com.coworkingservice.service.filter;

import com.coworkingservice.entity.Slot;

import java.util.Comparator;

public class TimeFilter implements Comparator<Slot> {
    public TimeFilter() {
    }
    @Override
    public int compare(Slot slot1, Slot slot2) {
        return slot1.getFromLocalDateTime().compareTo(slot2.getFromLocalDateTime());
    }
}
