package com.example.demo.security.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuth2UserDetails implements OAuth2User {
    private UserDetails user;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getName() {
        return user.getUsername();
    }

    public static OAuth2UserDetails create(OAuth2Resource resource, UserDetails user) {
        return new OAuth2UserDetails(user, resource.getAttributes(), user.getAuthorities());
    }
}