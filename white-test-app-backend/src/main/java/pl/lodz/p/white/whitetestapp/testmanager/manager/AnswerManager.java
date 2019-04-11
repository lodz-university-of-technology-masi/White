package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Answer;
import pl.lodz.p.white.whitetestapp.repository.AnswerRepository;
import pl.lodz.p.white.whitetestapp.testmanager.service.AnswerService;

@Service
public class AnswerManager implements AnswerService {

    AnswerRepository repository;

    @Override
    public Answer getOne(Long id) {
        return repository.getOne(id);
    }

    @Autowired
    public AnswerManager(AnswerRepository repository) {
        this.repository = repository;
    }
}
