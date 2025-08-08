package com.example.demo.controller.post.dto;

import com.example.demo.controller.user.dto.UserSimpleResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Integer id;
    private String name;
    private String content;
    private List<CommentResponseDto> comments;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private UserSimpleResponseDto createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private UserSimpleResponseDto updatedBy;
}
