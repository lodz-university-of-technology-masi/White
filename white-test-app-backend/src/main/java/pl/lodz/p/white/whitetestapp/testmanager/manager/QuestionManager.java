package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.repository.QuestionRepository;
import pl.lodz.p.white.whitetestapp.testmanager.service.QuestionService;

@Service
public class QuestionManager implements QuestionService {

    QuestionRepository repository;

    @Autowired
    public QuestionManager(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Question findOne(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public Long addNew(Question question) throws WrongRequestException {
        if (question.getQuestionType() != null) {
            return repository.save(question).getId();
        } else {
            throw new WrongRequestException(WrongRequestException.ARGUMENT_MISSING);
        }
    }
}
