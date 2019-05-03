package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;

    public Long getId() {
        return id;
    }

    public Answer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Answer setContent(String content) {
        this.content = content;
        return this;
    }
}
