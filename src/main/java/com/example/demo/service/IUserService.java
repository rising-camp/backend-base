package com.example.demo.service;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserResponseDto;

import java.util.List;

public interface IUserService {
    UserResponseDto findById(Integer id);

    List<UserResponseDto> findAll();

    UserResponseDto save(UserCreateRequestDto request);

    void delete(Integer id);
}
