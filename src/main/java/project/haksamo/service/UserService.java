package project.haksamo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.haksamo.dto.SignupDTO;
import project.haksamo.dto.SignupResponseDto;
import project.haksamo.entity.user.Academy;
import project.haksamo.entity.user.Student;
import project.haksamo.entity.user.Teacher;
import project.haksamo.entity.user.User;
import project.haksamo.repository.AcademyRepository;
import project.haksamo.repository.StudentRepository;
import project.haksamo.repository.TeacherRepository;
import project.haksamo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final AcademyRepository academyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public SignupResponseDto signupProcess(SignupDTO signupDTO){

        String username = signupDTO.getUsername();
        String password = signupDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if(isExist){
            SignupResponseDto signupResponseDto = new SignupResponseDto();
            signupResponseDto.setId(0);
            return signupResponseDto;
        }


        if(signupDTO.getRole().equals("teacher")){
            Teacher teacher = (Teacher) Teacher.builder()
                    .username(username)
                    .password(bCryptPasswordEncoder.encode(password))
                    .provider(User.Provider.APP)
                    .nickname(signupDTO.getNickname())
                    .build();

            teacherRepository.save(teacher);

            int userId = userRepository.findById(teacher.getUserId()).get().getUserId();
            SignupResponseDto signupResponseDto = new SignupResponseDto();
            signupResponseDto.setId(userId);
            signupResponseDto.setTeacherFlag(true);

            return signupResponseDto;
        }

        if(signupDTO.getRole().equals("student")){
            Student student = (Student) Student.builder()
                    .username(username)
                    .password(bCryptPasswordEncoder.encode(password))
                    .provider(User.Provider.APP)
                    .nickname(signupDTO.getNickname())
                    .build();;
            studentRepository.save(student);

            int userId = userRepository.findById(student.getUserId()).get().getUserId();
            SignupResponseDto signupResponseDto = new SignupResponseDto();
            signupResponseDto.setId(userId);
            signupResponseDto.setStudentFlag(true);

            return signupResponseDto;
        }

        if(signupDTO.getRole().equals("academy")){
            Academy academy = (Academy) Academy.builder()
                    .username(username)
                    .password(bCryptPasswordEncoder.encode(password))
                    .provider(User.Provider.APP)
                    .nickname(signupDTO.getNickname())
                    .build();

            academyRepository.save(academy);

            int userId = userRepository.findById(academy.getUserId()).get().getUserId();
            SignupResponseDto signupResponseDto = new SignupResponseDto();
            signupResponseDto.setId(userId);
            signupResponseDto.setAcademyFlag(true);

            return signupResponseDto;
        }
        return null;
    }

}
