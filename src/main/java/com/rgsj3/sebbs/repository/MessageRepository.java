package com.rgsj3.sebbs.repository;

import com.rgsj3.sebbs.domain.Message;
import com.rgsj3.sebbs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    public List<Message> findByToUserOrFromUserOrderByDateDesc(User toUser, User fromUser);
}
