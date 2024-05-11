package project.haksamo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.haksamo.dto.StudentSignUpDto;
import project.haksamo.dto.TeacherSingUpDto;
import project.haksamo.service.StudentService;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private StudentService studentService;

    @PostMapping("/signup/student")
    public int registerStudent(@RequestBody StudentSignUpDto studentSignUpDto, @RequestParam Integer userId){

        return studentService.singUp(studentSignUpDto,userId);
    }
}
