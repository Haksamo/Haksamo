package project.haksamo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.haksamo.dto.AcademySignUpDto;
import project.haksamo.service.AcademyService;

@RestController
@RequiredArgsConstructor
public class AcademyController {

    private AcademyService academyService;

    @PostMapping("/signup/academy")
    public int registerAcademy(@RequestBody AcademySignUpDto academySignUpDto, @RequestParam Integer userId){

        return academyService.singUp(academySignUpDto,userId);
    }
}
