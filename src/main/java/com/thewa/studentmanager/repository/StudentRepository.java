package com.thewa.studentmanager.repository;
import com.thewa.studentmanager.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
   List<Student> findByNameContaining(String name);
}