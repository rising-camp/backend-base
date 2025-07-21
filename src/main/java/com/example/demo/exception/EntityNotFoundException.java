package com.example.demo.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Object id) {
        super("데이터베이스 내 엔티티가 존재하지 않습니다. ID: " + id);
    }
}
