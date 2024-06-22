package com.coworkingservice.memorydb;

public interface CRUD<T> {
    void create();
    <V> V read(T key);
    void readAll();
    void update(T key);
    void delete(T Key);
}
