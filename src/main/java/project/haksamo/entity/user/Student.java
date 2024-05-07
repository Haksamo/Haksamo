package project.haksamo.entity.user;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import project.haksamo.entity.Address;

import java.time.LocalDateTime;

@Entity
@Getter
public class Student extends User{

    private String shortIntroduction;
    private int age;
    private boolean gender;
    private String wantedEducationStyle;

    @Embedded
    private Address address;
    private LocalDateTime authenticationTime;

}
