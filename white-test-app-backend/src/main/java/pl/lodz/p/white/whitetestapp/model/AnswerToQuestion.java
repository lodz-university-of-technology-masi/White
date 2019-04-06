package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Version;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;

@Entity
@Table
public class AnswerToQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "question_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Question question;

    @Column(length = 5000)
    private String answer;
    private Boolean isCorrect;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public AnswerToQuestion setId(Long id) {
        this.id = id;
        return this;
    }

    public Question getQuestion() {
        return question;
    }

    public AnswerToQuestion setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public AnswerToQuestion setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public AnswerToQuestion setCorrect(Boolean correct) {
        isCorrect = correct;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public AnswerToQuestion setVersion(Long version) {
        this.version = version;
        return this;
    }
}
