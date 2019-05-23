package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double time;
    private Double distance;
    private Long mouseClicks;

    public Long getId() {
        return id;
    }

    public Metric setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getTime() {
        return time;
    }

    public Metric setTime(Double time) {
        this.time = time;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public Metric setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public Long getMouseClicks() {
        return mouseClicks;
    }

    public Metric setMouseClicks(Long mouseClicks) {
        this.mouseClicks = mouseClicks;
        return this;
    }
}
