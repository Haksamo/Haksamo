package project.haksamo.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import project.haksamo.dto.CustomUserDetails;
import project.haksamo.entity.user.User;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization= request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("token null");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("token exists");

        //Bearer 부분 제거 후 순수 토큰만 획득, 토큰 소멸 시간 검증
        String token = authorization.split(" ")[1];
        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {
            //서버측에서 토큰이 만료되었다고 특정 응답 코드를 뱉어주면 그것을 토대로 프론트앤드측은 재요청을 진행해야 함.
                //따라서 재요청 부분은 모바일 앱에서 처리해야할 사항임.
            System.out.println("token expired");
            filterChain.doFilter(request, response); // 이거 주석처리해도 결과가 똑같은데, 필요한건가? 어차피 다음 필터(LoginFilter)가 작동이 안되는 것 같다.
            return;
        }
        System.out.println("token expiration time is ok, session let's go");

        //토큰에서 username 획득
        String username = jwtUtil.getUsername(token);
//        String provider = jwtUtil.getProvider(token); // 사용자 권한 관련 코드

        User user = User.builder()
                        .username(username)
                                .password("temppassword")
                                        .build();

//        userEntity.setProvider(User.Provider.valueOf(provider)); // 사용자 권한 관련 코드

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}
