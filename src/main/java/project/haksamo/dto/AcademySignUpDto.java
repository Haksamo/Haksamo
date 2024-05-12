package project.haksamo.dto;

import lombok.Data;
import project.haksamo.entity.Address;

import java.util.List;

@Data
public class AcademySignUpDto {

    private String shortIntroduction;
    private String target;
    private Address address;
    private List<String> classList;
    private String introduction;

}
