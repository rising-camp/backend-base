package com.example.demo.controller.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "글 작성을 위한 요청")
@AllArgsConstructor
public class PostCreateRequestDto {
    @NotBlank(message = "글 제목은 빈값이 될 수 없습니다. 최소 한글자를 입력하세요")
    @Size(max = 20, message = "글 제목은 리스트 내 잘 표기되도록 20자가 넘을 수 없습니다")
    @Schema(description = "글 제목", example = "글의 제목을 입력하세요")
    private String title;
    @NotBlank(message = "글 내용은 빈값이 될 수 없습니다. 최소 한글자를 입력하세요")
    @Size(min = 10, message = "최소한 글 내용은 10글자를 넘어야합니다")
    @Schema(description = "글 내용", example = "글의 내용을 입력하세요")
    private String content;
    @NotNull(message = "글을 작성한 유저가 누구인지 꼭 명시되어야합니다. 유저 아이디를 전송해주세요")
    @Schema(description = "작성자 유저 아이디", example = "1")
    private Integer userId;
}
