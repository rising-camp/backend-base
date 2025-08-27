package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.demo.security.OAuth2SuccessHandler.REDIRECT_URI;

@Component
public class JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter {
    private final static String REDIRECT_DEFAULT_PAGE = "/";
    private JwtProvider jwtProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {

        String token = jwtProvider.generate(authentication);
        String redirectUrl = getRedirectUrl(request, response);
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
//      response.sendRedirect(REDIRECT_URL);
    }

    public static String getRedirectUrl(HttpServletRequest request, HttpServletResponse response) {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        return savedRequest != null ? savedRequest.getRedirectUrl() : REDIRECT_DEFAULT_PAGE;
    }
}