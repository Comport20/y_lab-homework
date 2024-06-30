package com.coworkingservice.memorydb;

public interface Update<T> {
    boolean update(int indicator,T t);
}
