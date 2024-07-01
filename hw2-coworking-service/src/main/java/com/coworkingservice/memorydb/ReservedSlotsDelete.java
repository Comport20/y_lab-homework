package com.coworkingservice.memorydb;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ReservedSlotsDelete {
    boolean delete(int roomId, LocalDateTime localDateTime);
}
