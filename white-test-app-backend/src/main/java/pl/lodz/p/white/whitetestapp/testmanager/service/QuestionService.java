package pl.lodz.p.white.whitetestapp.testmanager.service;

import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.white.whitetestapp.model.Question;

public interface QuestionService {

    Question getOne(Long id);

    Question findOne(Long id);

    @Transactional
    Long addNew(Question question);
}
