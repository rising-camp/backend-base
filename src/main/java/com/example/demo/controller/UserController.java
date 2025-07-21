package com.example.demo.controller;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserResponseDto;
import com.example.demo.exception.ExceptionHandler;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> user(@PathVariable Integer id) {
//      Controller 는 단지 요청을 받고 실제 로직에 해당하는건 모두 Service 에 이관하고, 돌려받은 결과를 단지 반환할뿐이다.
        UserResponseDto responseDto = ExceptionHandler.execute(() -> userService.findById(id));
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> users() {
//      Controller 는 단지 요청을 받고 실제 로직에 해당하는건 모두 Service 에 이관하고, 돌려받은 결과를 단지 반환할뿐이다.
        List<UserResponseDto> responseDtos = ExceptionHandler.execute(userService::findAll);
        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateRequestDto request) {
//      Controller 는 단지 요청을 받고 실제 로직에 해당하는건 모두 Service 에 이관하고, 돌려받은 결과를 단지 반환할뿐이다.
        UserResponseDto responseDto = ExceptionHandler.execute(() -> userService.save(request));
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
//      Controller 는 단지 요청을 받고 실제 로직에 해당하는건 모두 Service 에 이관하고, 돌려받은 결과를 단지 반환할뿐이다.
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
