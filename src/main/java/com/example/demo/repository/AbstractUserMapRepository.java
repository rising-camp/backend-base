package com.example.demo.repository;

import com.example.demo.repository.entity.User;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.*;

public abstract class AbstractUserMapRepository implements IRepository<User, Integer> {
    protected static final Map<Integer, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.put(1, new User(1, "aaron", "123", "Aaron", 10, "DEVELOPER", "Backend", LocalDateTime.now().plusMinutes(10)));
        users.put(2, new User(2, "baron", "123", "Baron", 20, "DEVELOPER", "Frontend", LocalDateTime.now().plusMinutes(20)));
        users.put(3, new User(3, "caron", "123", "Caron", 30, "ENGINEER", "DevOps/SRE", LocalDateTime.now().plusMinutes(30)));
    }

    protected int idGenerate() {
        return Collections.max(users.keySet()) + 1;
    }
}
