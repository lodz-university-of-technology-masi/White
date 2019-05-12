package pl.lodz.p.white.whitetestapp.model;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
@Table
public class TestTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "position_id", referencedColumnName = "name")
    private Position position;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pl_id", referencedColumnName = "id")
    private TestTemplateContent plVersion;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "en_id", referencedColumnName = "id")
    private TestTemplateContent enVersion;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "author_id", referencedColumnName = "username")
    private Account author;

    private boolean isDeleted = false;

    private String name;

    public String getName() {
        return name;
    }

    public TestTemplate setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TestTemplate setId(Long id) {
        this.id = id;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public TestTemplate setPosition(Position position) {
        this.position = position;
        return this;
    }

    public TestTemplateContent getPlVersion() {
        return plVersion;
    }

    public TestTemplate setPlVersion(TestTemplateContent plVersion) {
        this.plVersion = plVersion;
        return this;
    }

    public TestTemplateContent getEnVersion() {
        return enVersion;
    }

    public TestTemplate setEnVersion(TestTemplateContent enVersion) {
        this.enVersion = enVersion;
        return this;
    }

    public Account getAuthor() {
        return author;
    }

    public TestTemplate setAuthor(Account author) {
        this.author = author;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
