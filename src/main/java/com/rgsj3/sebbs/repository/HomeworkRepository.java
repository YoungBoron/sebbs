package com.rgsj3.sebbs.repository;

import com.rgsj3.sebbs.domain.Course;
import com.rgsj3.sebbs.domain.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
    public List<Homework> findByCourse(Course course);
}
