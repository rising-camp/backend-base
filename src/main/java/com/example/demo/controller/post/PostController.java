package com.example.demo.controller.post;

import com.example.demo.controller.post.dto.PostCreateRequestDto;
import com.example.demo.controller.post.dto.PostResponseDto;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Post", description = "글 관련된 작업들, 글에 관련된 댓글 작업도 포함")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostController {
    PostService postService;
    CommentService commentService;

    @GetMapping("/{id}")
    @Operation(summary = "단일 글 조회", description = "주어진 PostId 에 해당하는 글을 하나 조회합니다.")
    public ResponseEntity<PostResponseDto> post(
            @Schema(description = "포스트 아이디", example = "1")
            @PathVariable Integer id
    ) {
        PostResponseDto post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("")
    @Operation(summary = "다수 글 조회", description = "누가 작성했는지 상관하지않고 모든 글들을 조회합니다.")
    public ResponseEntity<List<PostResponseDto>> posts() {
        List<PostResponseDto> posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("")
    @Operation(summary = "글 작성", description = "글을 작성합니다. 누가 작성했는지 유저 아이디를 같이 요청합니다.")
    public ResponseEntity<PostResponseDto> create(@RequestBody PostCreateRequestDto request) {
        PostResponseDto post = postService.save(request);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "글 삭제", description = "글을 삭제합니다. 요청에 주어진 PostId 에 해당하는 글을 삭제합니다.")
    public ResponseEntity<Void> delete(
            @Schema(description = "포스트 아이디", example = "1")
            @PathVariable Integer id
    ) {
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    @Operation(summary = "글에 작성된 댓글 삭제", description = "글에 작성되어있는 댓글을 삭제합니다. 누가 삭제했는지 따로 유저아이디를 받진않습니다.")
    public ResponseEntity<Void> delete(
            @Schema(description = "포스트 아이디", example = "1")
            @PathVariable Integer postId,
            @Schema(description = "댓글 아이디", example = "1")
            @PathVariable Integer commentId
    ) {
        commentService.delete(postId, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
