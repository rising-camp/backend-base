package com.example.demo.repository;

import com.example.demo.repository.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserQueryListRepository implements IRepository<User, Integer> {
    private static final List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        users.add(new User(1, "aaron", "123", "Aaron", 10, "DEVELOPER", "Backend", LocalDateTime.now().plusMinutes(10)));
        users.add(new User(2, "baron", "123", "Baron", 20, "DEVELOPER", "Frontend", LocalDateTime.now().plusMinutes(20)));
        users.add(new User(3, "caron", "123", "Caron", 30, "ENGINEER", "DevOps/SRE", LocalDateTime.now().plusMinutes(30)));
    }

    private int idGenerate() {
        return Collections.max(users.stream().map(User::getId).toList()) + 1;
    }

    public Optional<User> findById(Integer id) {
        return users.stream().filter(each -> id.equals(each.getId())).findFirst();
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User entity) {
        throw new RuntimeException("UserQueryListRepository 클래스는 Query 만 지원합니다 = INSERT(Command) 미지원");
    }

    public void delete(Integer id) {
        throw new RuntimeException("UserQueryListRepository 클래스는 Query 만 지원합니다 = DELETE(Command) 미지원");
    }
}