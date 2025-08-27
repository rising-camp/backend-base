package com.example.demo.security;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.User;
import com.example.demo.repository.entity.vo.Source;
import com.example.demo.security.vo.OAuth2KakaoResource;
import com.example.demo.security.vo.OAuth2Resource;
import com.example.demo.security.vo.OAuth2UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    private OAuth2Resource extract(String provider, OAuth2User resourceResponse) {
        return switch (Source.findByName(provider)) {
            case KAKAO -> OAuth2KakaoResource.create(resourceResponse);
            default -> throw new IllegalArgumentException("존재하지 않는 RegistrationId : " + provider);
        };
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest resourceRequest) throws OAuth2AuthenticationException {
        // OAuth2UserRequest : Resource (attributes) + AccessToken + ClientRegistration
        // OAuth2User        : Resource (attributes)
        // OAuth2Resource    : Resource (customized)
        final OAuth2User resourceResponse = super.loadUser(resourceRequest);
        final OAuth2Resource resource = extract(
                resourceRequest.getClientRegistration().getRegistrationId(),
                resourceResponse
        );

        UserDetails retrieved = userRepository.findByUsername(resource.getProviderId().toString())
                .orElseGet(() -> userRepository.save(User.create(resource)));

        return OAuth2UserDetails.create(resource, retrieved);
    }
}
