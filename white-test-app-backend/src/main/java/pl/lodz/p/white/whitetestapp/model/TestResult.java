package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "template_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TestTemplate testTemplate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AnswerToQuestion> answers;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public TestResult setId(Long id) {
        this.id = id;
        return this;
    }

    public TestTemplate getTestTemplate() {
        return testTemplate;
    }

    public TestResult setTestTemplate(TestTemplate testTemplate) {
        this.testTemplate = testTemplate;
        return this;
    }

    public List<AnswerToQuestion> getAnswers() {
        return answers;
    }

    public TestResult setAnswers(List<AnswerToQuestion> answers) {
        this.answers = answers;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public TestResult setVersion(Long version) {
        this.version = version;
        return this;
    }
}
