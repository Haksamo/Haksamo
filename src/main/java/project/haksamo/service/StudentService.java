package project.haksamo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.haksamo.dto.StudentSignUpDto;
import project.haksamo.dto.TeacherSingUpDto;
import project.haksamo.entity.Address;
import project.haksamo.entity.Class;
import project.haksamo.entity.user.Student;
import project.haksamo.entity.user.Teacher;
import project.haksamo.entity.user.User;
import project.haksamo.repository.StudentRepository;
import project.haksamo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;
    private UserRepository userRepository;
    private ClassService classService;

    public int singUp(StudentSignUpDto studentSignUpDto, Integer userId) {
        Student fidnStudent = studentRepository.findById(userId).get();
        User findUser = userRepository.findById(userId).get();

        fidnStudent.createDetails(studentSignUpDto.getShortIntroduction(),
                studentSignUpDto.getAge(),
                studentSignUpDto.isGender(),
                studentSignUpDto.getAddress(),
                studentSignUpDto.getIntroduction()
        );

        for (String subject : studentSignUpDto.getClassList()) {
            Class aClass = Class.builder()
                    .educationAddress(studentSignUpDto.getAddress())
                    .educationSubject(subject)
                    .build();

            aClass.addUser(findUser);
            classService.addClass(aClass);
        }
        return 1;
    }
}
