package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;

public interface TestTemplateContentRepository extends JpaRepository<TestTemplateContent, Long> {
}
