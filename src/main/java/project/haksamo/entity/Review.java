package project.haksamo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import project.haksamo.entity.user.User;

@Entity
@Getter
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    /**
     * 연관관계 메서드
     */
    public void addUser(User user){
        this.user = user;
        user.getReviewList().add(this);
    }
}
