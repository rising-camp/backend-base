package com.example.demo.security;

import com.example.demo.repository.entity.User;
import com.example.demo.security.vo.OAuth2UserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    public static final String REDIRECT_URI = "/set-token";
    public static final String REDIRECT_URL = "http://localhost:8080" + REDIRECT_URI;

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2UserDetails principal = (OAuth2UserDetails) authenticationToken.getPrincipal();
        User user = (User) principal.getUser();

        String token = jwtProvider.generate(authenticationToken);
        String redirectUrl = JwtAuthorizationFilter.getRedirectUrl(request, response);
        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60 * 60 * 24)
                .sameSite("Strict")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        response.sendRedirect(redirectUrl);

//      - Authorization 헤더를 통해 프론트엔드로 전달하는 방법 : 프론트엔드에서 JS 통해 LocalStorage / Cookie 내 수동 설정하여야함
//      response.addHeader(JwtProvider.AUTHORIZATION_HEADER, JwtProvider.BEARER_PREFIX + token);
//      getRedirectStrategy().sendRedirect(request, response, REDIRECT_URL);
    }
}
