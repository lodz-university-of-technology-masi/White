package pl.lodz.p.white.whitetestapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.AnswerToQuestion;
import pl.lodz.p.white.whitetestapp.repository.AnswerToQuestionRepository;
import pl.lodz.p.white.whitetestapp.service.AnswerToQuestionService;

@Service
public class AnswerToQuestionManager implements AnswerToQuestionService {

    @Autowired
    AnswerToQuestionRepository repository;

    @Override
    public AnswerToQuestion getOne(Long id) {
        return repository.getOne(id);
    }
}
