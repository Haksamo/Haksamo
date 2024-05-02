package project.haksamo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.haksamo.dto.TeacherSingUpDto;
import project.haksamo.entity.user.Teacher;
import project.haksamo.repository.TeacherRepository;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private TeacherRepository teacherRepository;

//    public int singUp(TeacherSingUpDto teacherDto){
//        Teacher teacher = new Teacher();
//        teacherRepository.save();
//        return 1;
//    }
}
