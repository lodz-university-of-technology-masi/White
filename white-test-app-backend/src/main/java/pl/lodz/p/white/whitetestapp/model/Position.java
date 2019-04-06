package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table
public class Position {
    @Id
    private String name;

    @Version
    private Long version;

    public String getName() {
        return name;
    }

    public Position setName(String name) {
        this.name = name;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public Position setVersion(Long version) {
        this.version = version;
        return this;
    }
}
