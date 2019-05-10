package pl.lodz.p.white.whitetestapp.testmanager.dtos;

import java.util.ArrayList;
import java.util.List;

public class CandidateTestResultRequest {
    private Long id;
    private Long testTemplateId;
    private List<CandidateAnswerToQuestionDto> answers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public CandidateTestResultRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTestTemplateId() {
        return testTemplateId;
    }

    public CandidateTestResultRequest setTestTemplateId(Long testTemplateId) {
        this.testTemplateId = testTemplateId;
        return this;
    }

    public List<CandidateAnswerToQuestionDto> getAnswers() {
        return answers;
    }

    public CandidateTestResultRequest setAnswers(List<CandidateAnswerToQuestionDto> answers) {
        this.answers = answers;
        return this;
    }
}
