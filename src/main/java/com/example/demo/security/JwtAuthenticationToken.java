package com.example.demo.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private String principal;
    private String credentials;

    public String getToken() {
        return this.credentials;
    }

    // 1. AuthenticationFilter 에서 가장 처음 인증정보를 담아 최초 JwtAuthenticationToken 생성
    public JwtAuthenticationToken(String token) {
        super(null);
        this.credentials = token;
        super.setAuthenticated(false);
    }

    // 2. AuthenticationProvider 에서 인증정보 일치여부를 판단한 뒤 최종 JwtAuthenticationToken 생성 (후에 SecurityContextHolder 내 저장할것)
    public JwtAuthenticationToken(String subject, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = subject;
        super.setAuthenticated(true);
    }
}
