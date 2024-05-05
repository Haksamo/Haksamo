package project.haksamo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.haksamo.entity.Class;
import project.haksamo.repository.ClassRepository;

@Service
@RequiredArgsConstructor
public class ClassService {

    private ClassRepository classRepository;

    public void addClass(Class aClass) {
        classRepository.save(aClass);
    }
}
