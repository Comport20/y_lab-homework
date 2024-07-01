package com.coworkingservice.service.filter;

import com.coworkingservice.entity.Slot;

import java.util.Comparator;

public class PriceFilter implements Comparator<Slot> {
    public PriceFilter() {
    }

    @Override
    public int compare(Slot slot1, Slot slot2) {
        return Double.compare(slot1.getRoom().getPrice(), slot2.getRoom().getPrice());
    }
}
