package org.dim4es.springapp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tag")
public class Tag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private List<Person> persons;


    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}