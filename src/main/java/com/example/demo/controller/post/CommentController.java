package com.example.demo.controller.post;

import com.example.demo.controller.post.dto.CommentCreateRequestDto;
import com.example.demo.controller.post.dto.CommentResponseDto;
import com.example.demo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comment", description = "댓글 관련된 작업들")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommentController {
    CommentService commentService;

    @PostMapping("")
    @Operation(summary = "댓글 작성", description = "글에 댓글을 작성합니다. 누가 작성했는지 유저 아이디를 같이 요청합니다.")
    public ResponseEntity<CommentResponseDto> create(@RequestBody CommentCreateRequestDto request) {
        CommentResponseDto comment = commentService.save(request);
        return ResponseEntity.ok(comment);
    }
}
