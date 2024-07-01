package com.coworkingservice.memorydb;

import java.time.LocalDate;

public interface ReadWhereIdAndDate<T> {
    T readWhereIdAndDate(int id, LocalDate Date);
}
