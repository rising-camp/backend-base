package com.example.demo.service;

import com.example.demo.controller.common.exceptions.BaseException;
import com.example.demo.controller.post.dto.PostCreateRequestDto;
import com.example.demo.controller.post.dto.PostResponseDto;
import com.example.demo.repository.post.PostRepository;
import com.example.demo.repository.post.entity.Post;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto findById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "게시글이 데이터베이스 내 존재하지 않습니다. 게시글 id : " + id));
        return PostResponseDto.from(post);
    }

    @Transactional
    public List<PostResponseDto> findAll() {
        return postRepository.findAll()
                .stream()
                .map(PostResponseDto::from)
                .toList();
    }

    @Transactional
    public PostResponseDto save(@Valid PostCreateRequestDto request) {
        // 작성자 User 조회
        Integer userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "유저가 데이터베이스 내 존재하지 않습니다. 유저 id : " + userId));
        // 작성한 Post 저장
        Post post = Post.create(
                request.getTitle(),
                request.getContent(),
                user
        );
        Post created = postRepository.save(post);
        return PostResponseDto.from(created);
    }

    @Transactional
    public void delete(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "게시글이 데이터베이스 내 존재하지 않습니다. 게시글 id : " + id));
        postRepository.deleteById(id);
    }
}