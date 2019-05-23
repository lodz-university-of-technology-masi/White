package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Position {
    @Id
    private String name;

    private boolean activated;

    public String getName() {
        return name;
    }

    public Position setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isActivated() {
        return activated;
    }

    public Position setActivated(boolean activated) {
        this.activated = activated;
        return this;
    }
}
