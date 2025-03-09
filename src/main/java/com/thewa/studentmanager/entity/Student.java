package com.thewa.studentmanager.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long studentId;
   private String name;
   private LocalDate dob;
   private String gender;
   private String email;
   private String mobile;
   private String parentName;
   private String parentNumber;
   
   @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<StudentAddress> addresses = new ArrayList<>();
   
   @ManyToMany
   @JoinTable(name = "student_course",
			  joinColumns = @JoinColumn(name = "student_id"),
			  inverseJoinColumns = @JoinColumn(name = "course_id"))
   private Set<Course> courses = new HashSet<>();
}