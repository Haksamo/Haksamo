package project.haksamo.dto;

import lombok.Data;

@Data
public class SignupResponseDto {
    private int id;
    private boolean studentFlag;
    private boolean teacherFlag;
    private boolean academyFlag;

}
