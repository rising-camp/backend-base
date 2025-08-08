package com.example.demo.controller.user;

import com.example.demo.controller.user.dto.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupController {

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDto> group(@PathVariable Integer id) {
    }

    @GetMapping("")
    public ResponseEntity<List<GroupResponseDto>> groups() {
    }

    @PostMapping("")
    public ResponseEntity<GroupResponseDto> create(@RequestBody GroupCreateRequestDto request) {
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDto> update(@PathVariable Integer id, @RequestBody GroupUpdateRequestDto request) {
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
    }
}
