package com.example.demo.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GroupUpdateRequestDto {
    private String name;
    private String desc;
    private List<Integer> userIds;
}