package com.example.demo.controller.user;

import com.example.demo.controller.user.dto.UserCreateRequestDto;
import com.example.demo.controller.user.dto.UserResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> user(@PathVariable Integer id) {
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> users() {
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateRequestDto request) {
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
    }
}
