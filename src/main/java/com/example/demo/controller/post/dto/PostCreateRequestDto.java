package com.example.demo.controller.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreateRequestDto {
    private String title;
    private String content;
    private Integer userId;
}
