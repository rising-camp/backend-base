package com.example.demo.controller.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "그룹 생성을 위한 정보들")
@AllArgsConstructor
public class GroupCreateRequestDto {
    @Schema(description = "그룹명", example = "예시 그룹명")
    private String name;
    @Schema(description = "그룹 설명", example = "본 그룹은 무엇을 위한것입니다")
    private String desc;
}