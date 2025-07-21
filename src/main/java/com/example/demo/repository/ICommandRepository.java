package com.example.demo.repository;

public interface ICommandRepository<T, ID> {
    T save(T entity);

    void delete(ID id);
}