package com.example.demo.repository;

import com.example.demo.repository.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class UserMapRepository implements IRepository<User, Integer> {
    private static final Map<Integer, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.put(1, new User(1, "aaron", "123", "Aaron", 10, "DEVELOPER", "Backend", LocalDateTime.now().plusMinutes(10)));
        users.put(2, new User(2, "baron", "123", "Baron", 20, "DEVELOPER", "Frontend", LocalDateTime.now().plusMinutes(20)));
        users.put(3, new User(3, "caron", "123", "Caron", 30, "ENGINEER", "DevOps/SRE", LocalDateTime.now().plusMinutes(30)));
    }

    private int idGenerate() {
        return Collections.max(users.keySet()) + 1;
    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> findAll() {
        return users.values().stream().toList();
    }

    public User save(User entity) {
        int generatedId = idGenerate();
        entity.setId(generatedId);
        users.put(generatedId, entity);
        return users.get(generatedId);
    }

    public void delete(Integer id) {
        users.remove(id);
    }
}