package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
