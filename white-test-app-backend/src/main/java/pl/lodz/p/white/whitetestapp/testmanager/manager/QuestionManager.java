package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.repository.QuestionRepository;
import pl.lodz.p.white.whitetestapp.testmanager.service.QuestionService;

@Service
public class QuestionManager implements QuestionService {

    QuestionRepository repository;

    @Override
    public Question getOne(Long id) {
        return repository.getOne(id);
    }

    @Autowired
    public QuestionManager(QuestionRepository repository) {
        this.repository = repository;
    }
}
