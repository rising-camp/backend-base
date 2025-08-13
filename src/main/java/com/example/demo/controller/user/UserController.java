package com.example.demo.controller.user;

import com.example.demo.controller.user.dto.UserCreateRequestDto;
import com.example.demo.controller.user.dto.UserResponseDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "유저에 관련된 작업들")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "단일 유저 조회", description = "주어진 UserId 에 해당하는 유저를 하나 조회합니다.")
    public ResponseEntity<UserResponseDto> user(@PathVariable Integer id) {
        UserResponseDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("")
    @Operation(summary = "다수 유저 조회", description = "검색 조건없이 모든 유저를 한번에 조회")
    public ResponseEntity<List<UserResponseDto>> users(@RequestParam(required = false) String username) {
        List<UserResponseDto> users = Optional.ofNullable(username)
                .map(userService::findByUsername)
                .orElseGet(userService::findAll);
        return ResponseEntity.ok(users);
    }

    @PostMapping("")
    @Operation(summary = "유저 생성", description = "최초 유저 생성, 사실상 회원가입과 동일")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateRequestDto request) {
        UserResponseDto user = userService.save(request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "유저 삭제", description = "유저를 삭제함과 동시에 유저가 속해있는 그룹들도 해산됩니다")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
