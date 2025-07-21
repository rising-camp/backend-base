package com.example.demo.repository;

import com.example.demo.repository.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserMapRepository extends AbstractUserMapRepository {

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