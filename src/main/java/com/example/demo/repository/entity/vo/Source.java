package com.example.demo.repository.entity.vo;

import java.util.Arrays;

public enum Source {
    HOMEPAGE,
    KAKAO,
    NAVER;

    public static Source findByName(String name) {
        return Arrays.stream(Source.values())
                .filter((each) -> each.name().equals(name.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Source 검색 : " + name.toUpperCase()));
    }
}
