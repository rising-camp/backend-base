package com.example.demo.controller.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupResponseDto {
    private Integer id;
    private String name;
    private String desc;
    private List<UserSimpleResponseDto> users;
}