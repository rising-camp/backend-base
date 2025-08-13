package com.example.demo.controller.post.dto;

import com.example.demo.controller.user.dto.UserSimpleResponseDto;
import com.example.demo.repository.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Schema(description = "글 정보")
@AllArgsConstructor
public class PostResponseDto {
    @Schema(description = "포스트 아이디", example = "1")
    private Integer id;
    @Schema(description = "글 제목", example = "유저가 입력했던 글 제목")
    private String title;
    @Schema(description = "글 내용", example = "유저가 입력했던 글 내용")
    private String content;
    @Schema(description = "글에 달린 댓글들")
    private List<CommentResponseDto> comments;
    @Schema(description = "작성일", example = "2000-01-01 10:30:50")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @Schema(description = "작성자")
    private UserSimpleResponseDto createdBy;
    @Schema(description = "수정일", example = "2000-01-01 10:30:50")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    @Schema(description = "수정자")
    private UserSimpleResponseDto updatedBy;

    public static PostResponseDto from(Post entity) {
        return new PostResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getComments().stream().map(CommentResponseDto::from).toList(),
                entity.getCreatedAt(),
                UserSimpleResponseDto.from(entity.getCreatedBy()),
                entity.getUpdatedAt(),
                UserSimpleResponseDto.from(entity.getUpdatedBy())
        );
    }
}
