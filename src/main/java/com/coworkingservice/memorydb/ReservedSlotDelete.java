package com.coworkingservice.memorydb;

import java.time.LocalDateTime;

public interface ReservedSlotDelete {
    boolean delete(int roomId, LocalDateTime localDateTime);
}
