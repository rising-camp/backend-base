package com.example.demo.repository;

import com.example.demo.repository.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, IUserRepositoryCustom {

    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    User save(User entity);

    void deleteById(Integer id);
}