package pl.lodz.p.white.whitetestapp.testmanager.dtos;

public class NewTestTemplateRequest {
    private Long id;
    private String testName;
    private String position;
    private String author;
    private String lang;

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
}