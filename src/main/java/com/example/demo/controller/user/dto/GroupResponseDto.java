package com.example.demo.controller.user.dto;

import com.example.demo.repository.user.entity.Allocated;
import com.example.demo.repository.user.entity.Group;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "그룹 정보")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupResponseDto {
    @Schema(description = "그룹 아이디", example = "1")
    private Integer id;
    @Schema(description = "그룹명", example = "예시 그룹명")
    private String name;
    @Schema(description = "그룹 설명", example = "본 그룹은 무엇을 위한것입니다")
    private String desc;
    @Schema(description = "그룹에 할당되어있는 유저들")
    private List<UserSimpleResponseDto> users;

    public static GroupResponseDto from(Group entity) {
        return new GroupResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDesc(),
                entity.getAllocates().stream()
                        .map(Allocated::getUser)
                        .map(UserSimpleResponseDto::from)
                        .toList()
        );
    }
}