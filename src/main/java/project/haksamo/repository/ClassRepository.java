package project.haksamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.haksamo.entity.Class;

public interface ClassRepository extends JpaRepository<Class,Integer> {
}
