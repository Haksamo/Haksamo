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

    public int singUp(TeacherSingUpDto teacherDto, Integer userId){
        Teacher findTeacher = teacherRepository.findById(userId).get();
        User findUser = userRepository.findById(userId).get();

        findTeacher.createDetails(teacherDto.getShortIntroduction(),
                teacherDto.getYear(),
                teacherDto.isGender(),
                teacherDto.getSchool(),
                teacherDto.getGraduationCondition(),
                teacherDto.getEducationStyle(),
                teacherDto.getSchoolAddress()
                );

        for(Address address:teacherDto.getAddressList()){
            for(String subject:teacherDto.getClassList()){
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
