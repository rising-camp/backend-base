package com.example.demo.security.vo;

import com.example.demo.repository.entity.vo.Source;

import java.util.Map;

public interface OAuth2Resource {
    Source getProvider();
    Long getProviderId();
    Map<String, Object> getAttributes();
    Map<String, Object> getAccount();
    Map<String, Object> getProfile();
    String getEmail();
}