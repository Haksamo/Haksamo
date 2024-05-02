package project.haksamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.haksamo.entity.user.Academy;

public interface AcademyRepository extends JpaRepository<Academy,Integer> {
}
