package pl.lodz.p.white.whitetestapp.testmanager.dtos;

import java.util.ArrayList;
import java.util.List;

public class TestCheckRequest {
    private long id;
    private List<QuestionCheckDto> questionChecks = new ArrayList<>();

    public long getId() {
        return id;
    }

    public TestCheckRequest setId(long id) {
        this.id = id;
        return this;
    }

    public List<QuestionCheckDto> getQuestionChecks() {
        return questionChecks;
    }

    public TestCheckRequest setQuestionChecks(List<QuestionCheckDto> questionChecks) {
        this.questionChecks = questionChecks;
        return this;
    }
}
