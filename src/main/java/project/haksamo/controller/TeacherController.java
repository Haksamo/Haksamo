package project.haksamo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.haksamo.dto.TeacherSingUpDto;
import project.haksamo.service.TeacherService;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private TeacherService teacherService;

//    @PostMapping("/signup/teacher")
//    public int registerTeacher(@RequestBody TeacherSingUpDto teacherDto){
//
////        return teacherService.singUp(teacherDto);
//    }
}
