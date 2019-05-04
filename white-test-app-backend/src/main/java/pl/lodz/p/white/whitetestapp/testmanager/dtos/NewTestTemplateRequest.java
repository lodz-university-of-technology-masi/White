package pl.lodz.p.white.whitetestapp.testmanager.dtos;

import pl.lodz.p.white.whitetestapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class NewTestTemplateRequest {
    private Long id;
    private String testName;
    private String position;
    private String author;
    private String lang;
    private List<Question> questions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public NewTestTemplateRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTestName() {
        return testName;
    }

    public NewTestTemplateRequest setTestName(String testName) {
        this.testName = testName;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public NewTestTemplateRequest setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public NewTestTemplateRequest setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public NewTestTemplateRequest setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public NewTestTemplateRequest setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }
}
