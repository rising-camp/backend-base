package com.example.demo.controller.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "유저 생성을 위한 정보들")
@AllArgsConstructor
public class UserCreateRequestDto {
    @Schema(description = "유저아이디", example = "aaron")
    private String username;
    @Schema(description = "비밀번호", example = "!@#$%")
    private String password;
    @Schema(description = "유저이름", example = "Aaron Ryu")
    private String name;
    @Schema(description = "나이", example = "10")
    private Integer age;
    @Schema(description = "직업", example = "Developer")
    private String job;
    @Schema(description = "직무", example = "Senior Backend")
    private String specialty;
}