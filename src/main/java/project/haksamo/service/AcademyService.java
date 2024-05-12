package project.haksamo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.haksamo.dto.AcademySignUpDto;
import project.haksamo.entity.Address;
import project.haksamo.entity.Class;
import project.haksamo.entity.user.Academy;
import project.haksamo.entity.user.Teacher;
import project.haksamo.entity.user.User;
import project.haksamo.repository.AcademyRepository;
import project.haksamo.repository.StudentRepository;
import project.haksamo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AcademyService {

    private AcademyRepository academyRepository;
    private UserRepository userRepository;
    private ClassService classService;

    public int singUp(AcademySignUpDto academySignUpDto, Integer userId) {
        Academy findAcademy = academyRepository.findById(userId).get();
        User findUser = userRepository.findById(userId).get();

        findAcademy.createDetails(
                academySignUpDto.getShortIntroduction(),
                academySignUpDto.getTarget(),
                academySignUpDto.getAddress(),
                academySignUpDto.getIntroduction()
        );

            for(String subject:academySignUpDto.getClassList()){
                Class aClass = Class.builder()
                        .educationAddress(academySignUpDto.getAddress())
                        .educationSubject(subject)
                        .build();

                aClass.addUser(findUser);
                classService.addClass(aClass);
            }
        return 1;
    }
}
