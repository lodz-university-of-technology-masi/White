package pl.lodz.p.white.whitetestapp.testmanager.dtos;

public class TestResultDetailResponse {
    private long testTemplateId;
    private long id;
    private String participant;
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

    public String getParticipant() {
        return participant;
    }

    public TestResultDetailResponse setParticipant(String participant) {
        this.participant = participant;
        return this;
    }

    public String getTestName() {
        return testName;
    }

    public TestResultDetailResponse setTestName(String testName) {
        this.testName = testName;
        return this;
    }
}
