package pl.lodz.p.white.whitetestapp.testmanager.dtos;

import java.util.ArrayList;
import java.util.List;

public class TestResultDetailResponse {
    private long testTemplateId;
    private long id;
    private List<QuestionCheckDto> questionChecks = new ArrayList<>();
    private String testName;

    public long getTestTemplateId() {
        return testTemplateId;
    }

    public TestResultDetailResponse setTestTemplateId(long testTemplateId) {
        this.testTemplateId = testTemplateId;
        return this;
    }

    public long getId() {
        return id;
    }

    public TestResultDetailResponse setId(long id) {
        this.id = id;
        return this;
    }

    public String getTestName() {
        return testName;
    }

    public List<QuestionCheckDto> getQuestionChecks() {
        return questionChecks;
    }

    public TestResultDetailResponse setQuestionChecks(List<QuestionCheckDto> questionChecks) {
        this.questionChecks = questionChecks;
        return this;
    }

    public TestResultDetailResponse setTestName(String testName) {
        this.testName = testName;
        return this;
    }
}
