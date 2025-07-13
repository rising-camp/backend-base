package com.example.demo.controller;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserResponseDto;
import com.example.demo.controller.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
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

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> user(@PathVariable Integer id) {
        Optional<User> retrieved = Optional.ofNullable(users.get(id));
        User user = retrieved
                .orElseThrow(() -> new RuntimeException("유저가 데이터베이스 내 존재하지 않습니다. 유저 id : " + id));
        UserResponseDto responseDto = UserResponseDto.from(user);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> users() {
        List<User> retrieved = users.values().stream().toList();
        List<UserResponseDto> responseDtos = retrieved.stream()
                .map(UserResponseDto::from)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateRequestDto request) {
        User entity = User.create(
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getAge(),
                request.getJob(),
                request.getSpecialty()
        );
        int generatedId = idGenerate();
        entity.setId(generatedId);
        users.put(generatedId, entity);
        User created = users.get(generatedId);
        UserResponseDto responseDto = UserResponseDto.from(created);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<User> retrieved = Optional.ofNullable(users.get(id));
        User user = retrieved
                .orElseThrow(() -> new RuntimeException("유저가 데이터베이스 내 존재하지 않습니다. 유저 id : " + id));
        users.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
