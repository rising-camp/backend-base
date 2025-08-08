package com.example.demo.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequestDto {
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String job;
    private String specialty;
}