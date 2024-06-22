package com.coworkingservice.service.filter;

import com.coworkingservice.entity.Slot;

import java.util.Comparator;

public class PersonFilter  implements Comparator<Slot> {
    public PersonFilter() {
    }

    @Override
    public int compare(Slot slot1, Slot slot2) {
        return slot1.getPerson().getFirstname().compareTo(slot2.getPerson().getFirstname());
    }
}
