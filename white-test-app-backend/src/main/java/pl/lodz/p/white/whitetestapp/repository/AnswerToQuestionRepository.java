package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.AnswerToQuestion;

public interface AnswerToQuestionRepository extends JpaRepository<AnswerToQuestion, Long> {
}
