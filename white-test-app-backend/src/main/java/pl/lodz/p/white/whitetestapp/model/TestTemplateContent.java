package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class TestTemplateContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> users;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public TestTemplateContent setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public TestTemplateContent setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public List<Account> getUsers() {
        return users;
    }

    public TestTemplateContent setUsers(List<Account> users) {
        this.users = users;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public TestTemplateContent setVersion(Long version) {
        this.version = version;
        return this;
    }
}
