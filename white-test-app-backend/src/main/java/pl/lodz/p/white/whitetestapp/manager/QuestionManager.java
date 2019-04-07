package pl.lodz.p.white.whitetestapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.repository.QuestionRepository;
import pl.lodz.p.white.whitetestapp.service.QuestionService;

@Service
public class QuestionManager implements QuestionService {

    @Autowired
    QuestionRepository repository;

    @Override
    public Question getOne(Long id) {
        return repository.getOne(id);
    }
}
