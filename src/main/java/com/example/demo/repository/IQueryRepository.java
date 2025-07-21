package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

public interface IQueryRepository<T, ID> {
    Optional<T> findById(ID id);

    List<T> findAll();
}