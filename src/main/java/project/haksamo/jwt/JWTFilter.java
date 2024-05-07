package project.haksamo.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import project.haksamo.dto.CustomOAuth2User;
import project.haksamo.dto.CustomUserDetails;
import project.haksamo.entity.user.User;


import java.io.IOException;
import java.util.Arrays;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("Authorization")) {
                        authorization = cookie.getValue();
                    }
                }
            } else {
                System.out.println("token null1");
                filterChain.doFilter(request, response);
                return;
            }
        }
        if (authorization == null) {
            System.out.println("token null2");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("token exists");

        String token = authorization.startsWith("Bearer ") ? authorization.split(" ")[1] : authorization;

        // 토큰 소멸 시간 검증
        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {
            //서버측에서 토큰이 만료되었다고 특정 응답 코드를 뱉어주면 그것을 토대로 프론트앤드측은 재요청을 진행해야 함.
            //따라서 재요청 부분은 모바일 앱에서 처리해야할 사항임.
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("token expiration time is ok");

        String username = jwtUtil.getUsername(token);
        User user = User.builder()
                .username(username)
                .password("temppassword")
                .build();
        User.Provider provider = jwtUtil.getProvider(token);

        if (provider == User.Provider.APP){ // 앱 회원일 경우
            //UserDetails에 회원 정보 객체 담기
            CustomUserDetails customUserDetails = new CustomUserDetails(user);
            //스프링 시큐리티 인증 토큰 생성
            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            //세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);

        } else{ // OAuth2 회원일 경우
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(user);
            Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);

        }

    }
}
