package pl.lodz.p.white.whitetestapp.testmanager.service;

import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;

public interface TestTemplateContentService {

    TestTemplateContent getOne(Long id);

    TestTemplateContent findOne(Long id);

    int addQuestionToContent(TestTemplateContent content, Question question);
}
