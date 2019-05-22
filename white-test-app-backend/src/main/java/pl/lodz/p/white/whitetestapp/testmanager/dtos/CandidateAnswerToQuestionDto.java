package pl.lodz.p.white.whitetestapp.testmanager.dtos;

public class CandidateAnswerToQuestionDto {
    private Long id;
    private Long questionId;
    private String answer;

    public Long getId() {
        return id;
    }

    public CandidateAnswerToQuestionDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public CandidateAnswerToQuestionDto setQuestionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public CandidateAnswerToQuestionDto setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}
