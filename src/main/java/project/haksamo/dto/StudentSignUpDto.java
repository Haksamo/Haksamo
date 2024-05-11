package project.haksamo.dto;

import lombok.Data;
import project.haksamo.entity.Address;

import java.util.List;

@Data
public class StudentSignUpDto {

    private String shortIntroduction;
    private int age;
    private boolean gender;
    private Address address;
    private List<String> classList;
    private String introduction;
}
