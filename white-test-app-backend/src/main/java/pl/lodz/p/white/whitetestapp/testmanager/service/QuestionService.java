package pl.lodz.p.white.whitetestapp.testmanager.service;

import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Question;

public interface QuestionService {

    Question getOne(Long id);

    Question findOne(Long id) throws EntityNotFoundException;

    @Transactional
    Long addNew(Question question) throws WrongRequestException;
}
