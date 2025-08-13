package com.example.demo.controller.user.dto;

import com.example.demo.repository.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "단순한 유저 정보")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSimpleResponseDto {
    @Schema(description = "유저 아이디", example = "1")
    private Integer id;
    @Schema(description = "유저아이디", example = "aaron")
    private String username;
    @Schema(description = "유저이름", example = "Aaron Ryu")
    private String name;

    public static UserSimpleResponseDto from(User entity) {
        return new UserSimpleResponseDto(
                entity.getId(),
                entity.getUsername(),
                entity.getName()
        );
    }
}