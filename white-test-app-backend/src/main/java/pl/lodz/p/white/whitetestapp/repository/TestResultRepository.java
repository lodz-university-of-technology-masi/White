package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.TestResult;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}
