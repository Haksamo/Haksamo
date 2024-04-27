package project.haksamo.dto;

import lombok.Data;

@Data
public class SignupDTO {

    private String username; // username = email, Spring Security 때문에 username으로 설정함
    private String password;

}
