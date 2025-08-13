package com.example.demo.service;

import com.example.demo.controller.common.exceptions.BaseException;
import com.example.demo.controller.user.dto.*;
import com.example.demo.repository.user.AllocatedRepository;
import com.example.demo.repository.user.GroupRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.Allocated;
import com.example.demo.repository.user.entity.Group;
import com.example.demo.repository.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final AllocatedRepository allocatedRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Transactional
    public GroupResponseDto findById(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "그룹이 데이터베이스 내 존재하지 않습니다. 그룹 id : " + id));
        return GroupResponseDto.from(group);
    }

    @Transactional
    public List<GroupResponseDto> findAll() {
        return groupRepository.findAll()
                .stream()
                .map(GroupResponseDto::from)
                .toList();
    }

    @Transactional
    public GroupResponseDto save(GroupCreateRequestDto request) {
        Group group = Group.create(
                request.getName(),
                request.getDesc()
        );
        Group created = groupRepository.save(group);
        return GroupResponseDto.from(created);
    }

    @Transactional
    public GroupResponseDto update(Integer groupId, GroupUpdateRequestDto request) {
        // Group 조회
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "그룹이 데이터베이스 내 존재하지 않습니다. 그룹 id : " + groupId));
        // Users 조회
        List<User> users = request.getUserIds().stream()
                .map((id) -> userRepository.findById(id)
                        .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "유저가 데이터베이스 내 존재하지 않습니다. 유저 id : " + id))
                ).toList();
        // Group 내 Users 할당 - Allocated (Associative Entity 가 대신)
        List<Allocated> allocates = users.stream()
                .map(user -> Allocated.create(group, user))
                .toList();
        allocatedRepository.saveAll(allocates);
        // Group 갱신 및 저장 (Dirty Checking)
        group.update(request.getName(), request.getDesc());
        return GroupResponseDto.from(group);
    }

    @Transactional
    public void delete(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "그룹이 데이터베이스 내 존재하지 않습니다. 그룹 id : " + id));
        groupRepository.deleteById(id);
    }
}