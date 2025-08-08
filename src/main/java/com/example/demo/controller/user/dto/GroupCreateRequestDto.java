package com.example.demo.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupCreateRequestDto {
    private String name;
    private String desc;
}