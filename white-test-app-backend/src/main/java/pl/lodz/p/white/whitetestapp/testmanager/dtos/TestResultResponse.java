package pl.lodz.p.white.whitetestapp.testmanager.dtos;

public class TestResultResponse {
    private long id;
    private String participant;
    private String testName;

    public long getId() {
        return id;
    }

    public TestResultResponse setId(long id) {
        this.id = id;
        return this;
    }

    public String getParticipant() {
        return participant;
    }

    public TestResultResponse setParticipant(String participant) {
        this.participant = participant;
        return this;
    }

    public String getTestName() {
        return testName;
    }

    public TestResultResponse setTestName(String testName) {
        this.testName = testName;
        return this;
    }
}
