package pl.lodz.p.white.whitetestapp.testmanager.dtos;

public class QuestionCheckDto {
    private long id;
    private boolean isCorrect;

    public long getId() {
        return id;
    }

    public QuestionCheckDto setId(long id) {
        this.id = id;
        return this;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public QuestionCheckDto setCorrect(boolean correct) {
        isCorrect = correct;
        return this;
    }
}
