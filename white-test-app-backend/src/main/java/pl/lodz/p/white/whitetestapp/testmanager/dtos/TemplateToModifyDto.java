package pl.lodz.p.white.whitetestapp.testmanager.dtos;

import pl.lodz.p.white.whitetestapp.model.Question;

import java.util.List;

public class TemplateToModifyDto {
    private Long id;
    private List<Question> questions;

    public Long getId() {
        return id;
    }

    public TemplateToModifyDto setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public TemplateToModifyDto setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }
}
