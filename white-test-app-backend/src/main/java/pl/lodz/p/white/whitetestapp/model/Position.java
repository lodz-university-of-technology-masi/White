package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Position {
    @Id
    private String name;

    public String getName() {
        return name;
    }

    public Position setName(String name) {
        this.name = name;
        return this;
    }
}
