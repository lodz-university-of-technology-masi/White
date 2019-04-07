package pl.lodz.p.white.whitetestapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Answer;
import pl.lodz.p.white.whitetestapp.repository.AnswerRepository;
import pl.lodz.p.white.whitetestapp.service.AnswerService;

@Service
public class AnswerManager implements AnswerService {

    @Autowired
    AnswerRepository repository;

    @Override
    public Answer getOne(Long id) {
        return repository.getOne(id);
    }
}
