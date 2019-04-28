package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;

public interface TestTemplateRepository extends JpaRepository<TestTemplate, Long> {
}
