package project.haksamo.entity.user;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import project.haksamo.entity.Address;

@Entity
@Getter
public class Teacher extends User{

    private String nickname;
    private int year;
    private int month;
    private int day;
    private boolean gender;
    private String school;

    @Embedded
    private Address campusAddress;

    private String major;
    private String graduationCondition;
    private String educationStyle;
    private String selfIntroduction;
    private String shortIntroduction;
    private int count;

}
