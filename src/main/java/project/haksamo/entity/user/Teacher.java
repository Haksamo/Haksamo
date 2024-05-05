package project.haksamo.entity.user;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import project.haksamo.entity.Address;

@Entity
@Getter
public class Teacher extends User{

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


    public void createDetails(String shortIntroduction, int year, boolean gender,
                              String school, String graduationCondition, String educationStyle,
                              Address schoolAddress) {
        this.selfIntroduction = shortIntroduction;
        this.year = year;
        this.gender = gender;
        this.school = school;
        this.graduationCondition = graduationCondition;
        this.educationStyle = educationStyle;
        this.campusAddress= schoolAddress;
    }
}
