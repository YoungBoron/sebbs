package com.rgsj3.sebbs.repository;

import com.rgsj3.sebbs.domain.Board;
import com.rgsj3.sebbs.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    public List<Topic> findByBoardAndUpOrderByReplyDateDesc(Board board, Boolean up);
    public Page<Topic> findByBoardOrderByReplyDateDesc(Board board, Pageable pageable);
}
