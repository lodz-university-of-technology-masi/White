package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
