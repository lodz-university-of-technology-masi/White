package pl.lodz.p.white.whitetestapp.testmanager.dtos;

public class TestTemplateResponse {
    private Long id;
    private String name;
    private String position;
    private String author;
    private String lang;
    private Long testTemplateId;

    public Long getId() {
        return id;
    }

    public TestTemplateResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestTemplateResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public TestTemplateResponse setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public TestTemplateResponse setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public TestTemplateResponse setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public Long getTestTemplateId() {
        return testTemplateId;
    }

    public TestTemplateResponse setTestTemplateId(Long testTemplateId) {
        this.testTemplateId = testTemplateId;
        return this;
    }
}
