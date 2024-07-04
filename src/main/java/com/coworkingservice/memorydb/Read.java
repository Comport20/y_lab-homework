package com.coworkingservice.memorydb;

import java.util.List;

public interface Read<T> {
    T read(int id);
    T readWhere(int indicator);
    List<T> readAll();
}
