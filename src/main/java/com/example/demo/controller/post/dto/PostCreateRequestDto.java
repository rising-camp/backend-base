package com.example.demo.controller.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "글 작성을 위한 요청")
@AllArgsConstructor
public class PostCreateRequestDto {
    @Schema(description = "글 제목", example = "글의 제목을 입력하세요")
    private String title;
    @Schema(description = "글 내용", example = "글의 내용을 입력하세요")
    private String content;
    @Schema(description = "작성자 유저 아이디", example = "1")
    private Integer userId;
}
