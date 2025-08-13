package com.example.demo.service;

import com.example.demo.controller.common.exceptions.BaseException;
import com.example.demo.controller.user.dto.UserCreateRequestDto;
import com.example.demo.controller.user.dto.UserResponseDto;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        this.save(new UserCreateRequestDto("aaron", "123", "Aaron", 10, "DEVELOPER", "Backend"));
        this.save(new UserCreateRequestDto("baron", "123", "Baron", 20, "DEVELOPER", "Frontend"));
        this.save(new UserCreateRequestDto("caron", "123", "Caron", 30, "ENGINEER", "DevOps/SRE"));
    }

    @Transactional
    public UserResponseDto findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "유저가 데이터베이스 내 존재하지 않습니다. 유저 id : " + id));
        return UserResponseDto.from(user);
    }

    @Transactional
    public List<UserResponseDto> findByUsername(String username) {
        List<User> users = userRepository.findByName(username);
        return users.stream()
                .map(UserResponseDto::from)
                .toList();
    }

    @Transactional
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::from)
                .toList();
    }

    @Transactional
    public UserResponseDto save(UserCreateRequestDto request) {
        User user = User.create(
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getAge(),
                request.getJob(),
                request.getSpecialty()
        );
        User created = userRepository.save(user);
        return UserResponseDto.from(created);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}