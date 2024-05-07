package project.haksamo.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import project.haksamo.entity.user.User;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public User.Provider getProvider(String token) {
        String provider = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("provider", String.class);
        return User.Provider.valueOf(provider);

    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // 토큰 생성
    public String createJwt(String username, User.Provider provider, Long expiredMs) {
//        토큰 만료시간 확인용 코드
//        Date date1 = new Date(System.currentTimeMillis());
//        Date date2 = new Date(System.currentTimeMillis() + expiredMs);
//        System.out.println("date 1 = " + date1);
//        System.out.println("date 2 = " + date2);

        return Jwts.builder()
                .claim("username", username)
                .claim("provider", provider.toString()) // Enum을 String으로 변환하여 저장, JWT에서 클레임의 값은 JSON 형태로 저장되며, 클레임의 값은 문자열 또는 기본 자료형으로 저장됨
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

}
