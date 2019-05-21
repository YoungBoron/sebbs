package com.rgsj3.sebbs.repository;

import com.rgsj3.sebbs.domain.Reply;
import com.rgsj3.sebbs.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    public Page<Reply> findByTopicOrderByFloorAsc(Topic topic, Pageable pageable);
}
