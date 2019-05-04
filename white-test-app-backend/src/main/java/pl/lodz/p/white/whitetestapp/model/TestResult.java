package pl.lodz.p.white.whitetestapp.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "template_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TestTemplate testTemplate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AnswerToQuestion> answers;

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
}
