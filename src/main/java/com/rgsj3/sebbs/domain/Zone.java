package com.rgsj3.sebbs.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Zone {
    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "名字不能为空")
    private String name;

    @OneToMany(targetEntity=Board.class, mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boardList;

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
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

    public Zone() {
    }
}
