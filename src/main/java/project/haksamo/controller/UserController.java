package project.haksamo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.haksamo.dto.SignupDTO;
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
    public String signup(@RequestBody SignupDTO signupDTO){
        userService.signupProcess(signupDTO);
        return "ok";
    }

}
