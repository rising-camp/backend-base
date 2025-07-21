package com.example.demo.exception;

public class EntitySaveFailedException extends RuntimeException {
    public EntitySaveFailedException(Object entity) {
        super("데이터베이스 내 엔티티가 잘 저장되지 않았습니다. 정보: " + entity);
    }
}
