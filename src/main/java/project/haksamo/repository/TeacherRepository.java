package project.haksamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.haksamo.entity.user.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
