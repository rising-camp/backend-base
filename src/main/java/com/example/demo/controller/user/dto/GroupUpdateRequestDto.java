package com.example.demo.controller.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "그룹 내 할당할 유저정보와 수정할 그룹 정보들")
@AllArgsConstructor
public class GroupUpdateRequestDto {
    @Schema(description = "그룹명", example = "예시 그룹명")
    private String name;
    @Schema(description = "그룹 설명", example = "본 그룹은 무엇을 위한것입니다")
    private String desc;
    @Schema(description = "그룹에 할당하고자하는 유저정보 (기등록 유저는 오류 발생)", example = "[1, 2, 3]")
    private List<Integer> userIds;
}