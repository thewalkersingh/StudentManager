package com.thewa.studentmanager.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "student")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @EqualsAndHashCode.Include
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
   
   public void addCourse(Course course) {
	  this.courses.add(course);
	  course.getStudents().add(this);
   }
   
   public void removeCourse(Course course) {
	  this.courses.remove(course);
	  course.getStudents().remove(this);
   }
}