package com.example.demo.service;

import com.example.demo.controller.common.exceptions.BaseException;
import com.example.demo.controller.post.dto.CommentCreateRequestDto;
import com.example.demo.controller.post.dto.CommentResponseDto;
import com.example.demo.repository.post.CommentRepository;
import com.example.demo.repository.post.PostRepository;
import com.example.demo.repository.post.entity.Comment;
import com.example.demo.repository.post.entity.Post;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponseDto save(CommentCreateRequestDto request) {
        // 원본글 Post 조회
        Integer postId = request.getPostId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "포스트가 데이터베이스 내 존재하지 않습니다. 포스트 id : " + postId));
        // 작성자 User 조회
        Integer userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "유저가 데이터베이스 내 존재하지 않습니다. 유저 id : " + userId));
        // 작성한 Comment 저장
        Comment comment = Comment.create(
                request.getContent(),
                post,
                user
        );
        Comment created = commentRepository.save(comment);
        return CommentResponseDto.from(created);
    }

    @Transactional
    public void delete(Integer postId, Integer commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "포스트가 데이터베이스 내 존재하지 않습니다. 포스트 id : " + postId));
        Comment found = post.getComments().stream()
                .filter((comment) -> commentId.equals(comment.getId()))
                .findFirst()
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "포스트 내 해당 댓글이 존재하지 않습니다. 포스트 id : " + postId + " - 댓글 id: " + commentId));
        post.getComments().remove(found);
    }
}