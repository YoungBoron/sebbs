package com.rgsj3.sebbs.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String glyphicon;
    private String description;

    @ManyToOne(targetEntity = Zone.class)
    @JoinColumn(name = "zid", referencedColumnName="id")
    private Zone zone;

    @OneToMany(targetEntity=Topic.class, mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Topic> topicList;

    public String getGlyphicon() {
        return glyphicon;
    }

    public void setGlyphicon(String glyphicon) {
        this.glyphicon = glyphicon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public Board() {
    }
}
