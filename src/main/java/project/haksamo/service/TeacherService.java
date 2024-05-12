package project.haksamo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.haksamo.dto.TeacherSingUpDto;
import project.haksamo.entity.Address;
import project.haksamo.entity.Class;
import project.haksamo.entity.user.Teacher;
import project.haksamo.entity.user.User;
import project.haksamo.repository.TeacherRepository;
import project.haksamo.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherService {

    private TeacherRepository teacherRepository;
    private UserRepository userRepository;
    private ClassService classService;

    public int singUp(TeacherSingUpDto teacherSingUpDto, Integer userId){
        Teacher findTeacher = teacherRepository.findById(userId).get();
        User findUser = userRepository.findById(userId).get();

        findTeacher.createDetails(
                teacherSingUpDto.getShortIntroduction(),
                teacherSingUpDto.getYear(),
                teacherSingUpDto.isGender(),
                teacherSingUpDto.getSchool(),
                teacherSingUpDto.getSchoolAddress(),
                teacherSingUpDto.getMajor(),
                teacherSingUpDto.getGraduationCondition(),
                teacherSingUpDto.getIntroduction()
                );

        for(Address address:teacherSingUpDto.getAddressList()){
            for(String subject:teacherSingUpDto.getClassList()){
                Class aClass = Class.builder()
                        .educationAddress(address)
                        .educationSubject(subject)
                        .build();

                aClass.addUser(findUser);
                classService.addClass(aClass);
            }
        }
        return 1;
    }
}
