package project.haksamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.haksamo.entity.user.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
