package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@Table
public class Account {
    @Id
    private String username;

    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*" +
            "[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")
    private String email;
    private boolean deleted;
    private String passwordHash;

    @Enumerated(STRING)
    private Role2 role;

    @Enumerated(STRING)
    private Lang lang;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TestResult> testResults = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public Account setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Account setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Account setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public Role2 getRole() {
        return role;
    }

    public Account setRole(Role2 role) {
        this.role = role;
        return this;
    }

    public Lang getLang() {
        return lang;
    }

    public Account setLang(Lang lang) {
        this.lang = lang;
        return this;
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public Account setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
        return this;
    }

}
