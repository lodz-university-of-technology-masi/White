package pl.lodz.p.white.whitetestapp.testmanager.dtos;

public class QuestionCheckDto {
    private long id;
    private long questionId;
    private Boolean isCorrect = false;
    private String answer;

    public long getId() {
        return id;
    }

    public QuestionCheckDto setId(long id) {
        this.id = id;
        return this;
    }

    public long getQuestionId() {
        return questionId;
    }

    public QuestionCheckDto setQuestionId(long questionId) {
        this.questionId = questionId;
        return this;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public QuestionCheckDto setCorrect(Boolean correct) {
        isCorrect = correct;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public QuestionCheckDto setAnswer(String answer) {
        this.answer = answer;
        return this;
    }
}
