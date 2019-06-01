package com.rgsj3.sebbs.repository;

import com.rgsj3.sebbs.domain.Course;
import com.rgsj3.sebbs.domain.StudentCourse;
import com.rgsj3.sebbs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
    public Integer countByCourse(Course course);
    public List<StudentCourse> findByCourseOrderById(Course course);
    public StudentCourse getById(Integer id);
    public List<StudentCourse> findStudentCoursesByStudent(User user);
}
