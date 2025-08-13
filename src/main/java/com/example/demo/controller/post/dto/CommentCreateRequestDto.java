package com.example.demo.controller.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "댓글 작성을 위한 요청")
@AllArgsConstructor
public class CommentCreateRequestDto {
    @Schema(description = "댓글 내용", example = "댓글 내용을 입력하세요")
    private String content;
    @Schema(description = "포스트 아이디", example = "1")
    private Integer postId;
    @Schema(description = "작성자 유저 아이디", example = "1")
    private Integer userId;
}
