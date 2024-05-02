package project.haksamo.entity.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import project.haksamo.entity.Address;
import project.haksamo.entity.Class;
import project.haksamo.entity.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Setter 사용은 지양하는 것이 좋기에 @Data삭제 , setter만을 위한 @data는 엔티티에는 좋지 않음
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users") @Getter
public class User {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    // Spring Security에서는 username과 password를 받기 때문에 email이 아닌 username으로 설정(이후에 수정 예정)
        // username에 들어가는 데이터는 email 형태
    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Class> classList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false)
    private String password;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public enum Provider { APP, KAKAO, NAVER }

    public User() {
    }

    /**
     * Setter 대신 Builder패턴 롬복 제공 기능 이용 => UserService에서도 set을 사용한 객체 생성이 아닌 builder 패턴 사용
     */
    @Builder
    public User(String username, String password, Provider provider){
        this.username = username;
        this.password = password;
        this.provider = provider;
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