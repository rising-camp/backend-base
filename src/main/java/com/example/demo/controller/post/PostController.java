package com.example.demo.controller.post;

import com.example.demo.controller.post.dto.PostCreateRequestDto;
import com.example.demo.controller.post.dto.PostResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostController {

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> post(@PathVariable Integer id) {
    }

    @GetMapping("")
    public ResponseEntity<List<PostResponseDto>> posts() {
    }

    @PostMapping("")
    public ResponseEntity<PostResponseDto> create(@RequestBody PostCreateRequestDto request) {
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Integer postId, @PathVariable Integer commentId) {
    }
}
