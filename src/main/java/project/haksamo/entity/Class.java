package project.haksamo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import project.haksamo.entity.user.User;

@Entity
@Getter
public class Class {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int classId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Embedded
    private Address educationAddress;
    private String educationSubject;

    /**
     * 연관 관계 메서드
     */
    public void addUser(User user){
        this.user=user;
        user.getClassList().add(this);
    }

}
