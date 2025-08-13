package com.example.demo.controller.post.dto;

import com.example.demo.repository.post.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "댓글 정보")
@AllArgsConstructor
public class CommentResponseDto {
    @Schema(name = "댓글 아이디", description = "1")
    private Integer id;
    @Schema(name = "댓글 내용", description = "유저가 입력했던 댓글 내용")
    private String content;

    public static CommentResponseDto from(Comment entity) {
        return new CommentResponseDto(
                entity.getId(),
                entity.getContent()
        );
    }
}
