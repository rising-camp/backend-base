package com.example.demo.service;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserResponseDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        this.save(new UserCreateRequestDto("aaron", passwordEncoder.encode("123"), "Aaron", 10, "DEVELOPER", "Backend"));
        this.save(new UserCreateRequestDto("baron", passwordEncoder.encode("123"), "Baron", 20, "DEVELOPER", "Frontend"));
        this.save(new UserCreateRequestDto("caron", passwordEncoder.encode("123"), "Caron", 30, "ENGINEER", "DevOps/SRE"));
    }

    public UserResponseDto findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저가 데이터베이스 내 존재하지 않습니다. 유저 id : " + id));
        return UserResponseDto.from(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다 - username : " + username));
        return user;
    }

    public List<UserResponseDto> findByUsername(String username) {
        List<User> users = userRepository.findByName(username);
        return users.stream()
                .map(UserResponseDto::from)
                .toList();
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::from)
                .toList();
    }

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