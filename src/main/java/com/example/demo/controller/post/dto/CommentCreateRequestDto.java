package com.example.demo.controller.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateRequestDto {
    private String content;
    private Integer postId;
    private Integer userId;
}
