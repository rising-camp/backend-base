package com.example.demo.service;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserResponseDto;
import com.example.demo.repository.UserListRepository;
import com.example.demo.repository.IRepository;
import com.example.demo.repository.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final IRepository<User, Integer> userRepository;

    public UserService() {
        this.userRepository = new UserListRepository();
    }

    public UserResponseDto findById(Integer id) {
        User user = userRepository.findById(id)
//              일부 학생은 UserRepository 에 "유저 미존재 시 메세지 반환하는 로직"을 포함시켜야하지 않나 생각할 수 있음
//              어떤 데이터베이스를 쓰든 그 데이터베이스 로직에서 어떤 메세지를 표시할지 신경쓰지않는것이 맞다.
//              데이터베이스를 사용하는 개발자가 비지니스 요구사항에 맞춰 동일 데이터베이스일지라도 원하는 메세지를 바꿀 수 있도록 해야한다.
//              만약 메세지를 바꾸기 위해 데이터베이스 로직을 교체한다면 정말 멍청한짓이 아닐 수 없다.
                .orElseThrow(() -> new RuntimeException("유저가 데이터베이스 내 존재하지 않습니다. 유저 id : " + id));
//      데이터베이스는 외부에 어떻게 값을 반환할지 신경쓰지 않는다.
        return UserResponseDto.from(user);
    }

    public List<UserResponseDto> findAll() {
        List<User> retrieved = userRepository.findAll();
//      데이터베이스 로직은 단지 데이터베이스 내 데이터인 User 엔티티 객체를 반환하거나 조작할뿐
//      어떤 값들을 가져다 어떤 객체로 반환해야할지에 대한것은 데이터베이스가 신경 쓸 로직이 아니다.
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::from)
                .toList();
    }

    public UserResponseDto save(UserCreateRequestDto request) {
//      일부의 학생은 그냥 저장하고자하는 값들을 파라미터로 나열하여 save 함수로 전달하는걸 생각할 수 있지만
//      데이터베이스에 저장하고자하는 User 엔티티 객체를 보내주는것이 후에 Repository 인터페이스에 Generic(T) 적용할때 유리하다.
        User entity = User.create(
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getAge(),
                request.getJob(),
                request.getSpecialty()
        );
        User created = userRepository.save(entity);
//      데이터베이스는 외부에 어떻게 값을 반환할지 신경쓰지 않는다.
        return UserResponseDto.from(created);
    }

    public void delete(Integer id) {
        UserResponseDto user = this.findById(id);
        userRepository.delete(id);
    }
}
