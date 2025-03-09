package com.thewa.studentmanager.repository;
import com.thewa.studentmanager.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}