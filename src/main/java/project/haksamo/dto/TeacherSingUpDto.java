package project.haksamo.dto;

import lombok.Data;
import project.haksamo.entity.Address;
import project.haksamo.entity.Class;
import project.haksamo.entity.user.Teacher;
import project.haksamo.entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeacherSingUpDto {

    private String shortIntroduction;
    private int year;
    private boolean gender;
    private String school;
    private Address schoolAddress;
    private String graduationCondition;
    private String educationStyle;
    private List<String> classList;
    private List<Address> addressList;

//    public TeacherSingUpDto(Teacher teacher, User user) {
//        this.shortIntroduction = teacher.getShortIntroduction();
//        this.year = teacher.getYear();
//        this.gender = teacher.isGender();
//        this.school = teacher.getSchool();
//        this.graduationCondition = teacher.getGraduationCondition();
//        this.educationStyle=teacher.getEducationStyle();
//        this.classList = user.getClassList().stream()
//                .map(aClass -> new ClassDto(aClass))
//                .collect(Collectors.toList());
//    }
}
