package com.example.demo.controller.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSimpleResponseDto {
    private Integer id;
    private String username;
    private String name;
}