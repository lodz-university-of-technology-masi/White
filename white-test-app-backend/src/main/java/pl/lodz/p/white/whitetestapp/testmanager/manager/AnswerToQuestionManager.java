package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.AnswerToQuestion;
import pl.lodz.p.white.whitetestapp.repository.AnswerToQuestionRepository;
import pl.lodz.p.white.whitetestapp.testmanager.service.AnswerToQuestionService;

@Service
public class AnswerToQuestionManager implements AnswerToQuestionService {

    AnswerToQuestionRepository repository;

    @Override
    public AnswerToQuestion getOne(Long id) {
        return repository.getOne(id);
    }

    @Autowired
    public AnswerToQuestionManager(AnswerToQuestionRepository repository) {
        this.repository = repository;
    }
}
