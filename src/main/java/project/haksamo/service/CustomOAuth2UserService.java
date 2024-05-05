package project.haksamo.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import project.haksamo.dto.CustomOAuth2User;
import project.haksamo.dto.KakaoResponse;
import project.haksamo.dto.NaverResponse;
import project.haksamo.dto.OAuth2Response;
import project.haksamo.entity.user.User;
import project.haksamo.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        User.Provider provider = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());

            provider = User.Provider.NAVER;
        }
        else if (registrationId.equals("kakao")) {

            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());

            provider = User.Provider.KAKAO;
        }
        else {
            return null;
        }

        User existData = userRepository.findByUsername(oAuth2Response.getEmail());

        if (existData == null) {
            User user = User.builder()
                            .username(oAuth2Response.getEmail())
                                    .password("oauthtemppassword")
                                            .provider(provider)
                    .build();

            userRepository.save(user);

            return new CustomOAuth2User(user);
        }
        else {
            // 필요하다면 추가
            return new CustomOAuth2User(existData);
        }
    }

}
