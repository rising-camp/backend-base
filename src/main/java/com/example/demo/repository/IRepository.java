package com.example.demo.repository;

public interface IRepository<T, ID> extends IQueryRepository<T, ID>, ICommandRepository<T, ID> {
}