package project.haksamo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.haksamo.dto.SignupDTO;
import project.haksamo.dto.SignupResponseDto;
import project.haksamo.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public String mainP(){
        return "hello";
    }

    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody SignupDTO signupDTO){
        return userService.signupProcess(signupDTO);
    }


}
