package com.example.demo.controller.user;

import com.example.demo.controller.user.dto.*;
import com.example.demo.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@Tag(name = "Group", description = "유저들이 속하는 그룹에 관련된 작업들")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupController {
    GroupService groupService;

    @GetMapping("/{id}")
    @Operation(summary = "단일 그룹 조회", description = "어떤 유저들이 속해있는지 그룹 아이디로 단일 그룹 조회")
    public ResponseEntity<GroupResponseDto> group(@PathVariable Integer id) {
        GroupResponseDto group = groupService.findById(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping("")
    @Operation(summary = "다수 그룹 조회", description = "검색 조건없이 모든 그룹을 한번에 조회")
    public ResponseEntity<List<GroupResponseDto>> groups() {
        List<GroupResponseDto> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("")
    @Operation(summary = "그룹 생성", description = "유저 할당은 따로 하지않고 그룹만 생성")
    public ResponseEntity<GroupResponseDto> create(@RequestBody GroupCreateRequestDto request) {
        GroupResponseDto group = groupService.save(request);
        return ResponseEntity.ok(group);
    }

    @PutMapping("/{id}")
    @Operation(summary = "그룹 내 유저 할당 및 그룹정보 수정", description = "그룹에 유저를 할당하고 정보를 수정합니다")
    public ResponseEntity<GroupResponseDto> update(
            @Schema(description = "그룹 아이디", example = "1")
            @PathVariable Integer id,
            @RequestBody GroupUpdateRequestDto request
    ) {
        GroupResponseDto group = groupService.update(id, request);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "그룹 삭제", description = "그룹을 삭제함과 동시에 그룹에 속해있는 유저들도 해산됩니다")
    public ResponseEntity<Void> delete(
            @Schema(description = "그룹 아이디", example = "1")
            @PathVariable Integer id
    ) {
        groupService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
