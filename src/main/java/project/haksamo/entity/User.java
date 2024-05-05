package project.haksamo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    // Spring Security에서는 username과 password를 받기 때문에 email이 아닌 username으로 설정(이후에 수정 예정)
        // username에 들어가는 데이터는 email 형태
    @Column(name = "username", nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String nickname;

    // 임시로 String 타입으로 설정
    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column
    private Provider provider;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public enum Provider { APP, KAKAO, NAVER }

    @Builder
    public User(String username, String password, Provider provider, String nickname){
        this.username = username;
        this.password = password;
        this.provider = provider;
        this.nickname = nickname;
    }

}

//
// CREATE TABLE users (
//    user_id INT AUTO_INCREMENT PRIMARY KEY,
//    username VARCHAR(50) NOT NULL,
//    password VARCHAR(255) NOT NULL,
//    address VARCHAR(255),
//    provider ENUM('APP', 'KAKAO', 'NAVER'),
//    is_deleted BOOLEAN
//);