package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.*;

@Entity
@Table
public class TestTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "position_id", referencedColumnName = "name", insertable = false, updatable = false)
    private Position position;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pl_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TestTemplateContent plVersion;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "en_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TestTemplateContent enVersion;

    @Version
    private Long version;

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

    public Long getVersion() {
        return version;
    }

    public TestTemplate setVersion(Long version) {
        this.version = version;
        return this;
    }
}
