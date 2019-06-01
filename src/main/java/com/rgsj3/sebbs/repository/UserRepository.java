package com.rgsj3.sebbs.repository;

import com.rgsj3.sebbs.domain.Course;
import com.rgsj3.sebbs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findByName(String name);
}
