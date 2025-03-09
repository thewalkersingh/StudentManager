package com.thewa.studentmanager.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Course {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long courseId;
   private String courseName;
   private String description;
   private String courseType;
   private String duration;
   private String topics;
   
   @ManyToMany(mappedBy = "courses")
   private Set<Student> students = new HashSet<>();
}