package com.thewa.studentmanager.config;
import com.thewa.studentmanager.entity.Course;
import com.thewa.studentmanager.entity.Student;
import com.thewa.studentmanager.entity.StudentAddress;
import com.thewa.studentmanager.repository.CourseRepository;
import com.thewa.studentmanager.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DummyData implements CommandLineRunner {
   private final StudentRepository studentRepository;
   private final CourseRepository courseRepository;
   
   public DummyData(StudentRepository studentRepository, CourseRepository courseRepository) {
	  this.studentRepository = studentRepository;
	  this.courseRepository = courseRepository;
   }
   
   @Override public void run(String... args) throws Exception {
	  // Courses
	  Course javaCourse = new Course();
	  javaCourse.setCourseName("Java 101");
	  javaCourse.setDescription("Introduction to Java programming.");
	  javaCourse.setCourseType("Programming");
	  javaCourse.setDuration("3 months");
	  javaCourse.setTopics("Java, OOP, Basics");
	  courseRepository.save(javaCourse);
	  
	  Course springCourse = new Course();
	  springCourse.setCourseName("Spring Boot 101");
	  springCourse.setDescription("Learn Spring Boot framework.");
	  springCourse.setCourseType("Framework");
	  springCourse.setDuration("2 months");
	  springCourse.setTopics("Spring Boot, REST, Microservices");
	  courseRepository.save(springCourse);
	  
	  Course reactCourse = new Course();
	  reactCourse.setCourseName("React 101");
	  reactCourse.setDescription("Introduction to React.");
	  reactCourse.setCourseType("Frontend");
	  reactCourse.setDuration("1.5 months");
	  reactCourse.setTopics("React, JavaScript, Components");
	  courseRepository.save(reactCourse);
	  
	  // Student 1
	  Student student1 = new Student();
	  student1.setName("Rahul Sharma");
	  student1.setDob(LocalDate.of(2000, 4, 10));
	  student1.setGender("Male");
	  student1.setEmail("rahul.sharma@example.in");
	  student1.setMobile("9876543210");
	  student1.setParentName("Sunita Sharma");
	  student1.setParentNumber("9123456789");
	  
	  StudentAddress address1 = new StudentAddress();
	  address1.setArea("Andheri");
	  address1.setState("Maharashtra");
	  address1.setDistrict("Mumbai");
	  address1.setPincode("400069");
	  address1.setAddressType("Permanent");
	  address1.setStudent(student1);
	  student1.getAddresses().add(address1);
	  
	  // Student 2
	  Student student2 = new Student();
	  student2.setName("Priya Singh");
	  student2.setDob(LocalDate.of(1999, 6, 15));
	  student2.setGender("Female");
	  student2.setEmail("priya.singh@example.in");
	  student2.setMobile("9123456780");
	  student2.setParentName("Rajesh Singh");
	  student2.setParentNumber("9876543210");
	  
	  StudentAddress address2 = new StudentAddress();
	  address2.setArea("Connaught Place");
	  address2.setState("Delhi");
	  address2.setDistrict("New Delhi");
	  address2.setPincode("110001");
	  address2.setAddressType("Correspondence");
	  address2.setStudent(student2);
	  student2.getAddresses().add(address2);
	  
	  // Student 3
	  Student student3 = new Student();
	  student3.setName("Amit Kumar");
	  student3.setDob(LocalDate.of(2001, 9, 20));
	  student3.setGender("Male");
	  student3.setEmail("amit.kumar@example.in");
	  student3.setMobile("9988776655");
	  student3.setParentName("Ajankya Kanna");
	  student3.setParentNumber("8877665544");
	  
	  StudentAddress address3 = new StudentAddress();
	  address3.setArea("MG Road");
	  address3.setState("Karnataka");
	  address3.setDistrict("Bengaluru");
	  address3.setPincode("560001");
	  address3.setAddressType("Current");
	  address3.setStudent(student3);
	  student3.getAddresses().add(address3);
	  
	  // Assign courses
	  student1.getCourses().add(javaCourse);
	  student1.getCourses().add(springCourse);
	  studentRepository.save(student1);
	  
	  student2.getCourses().add(springCourse);
	  student2.getCourses().add(reactCourse);
	  studentRepository.save(student2);
	  
	  student3.getCourses().add(javaCourse);
	  student3.getCourses().add(reactCourse);
	  studentRepository.save(student3);
   }
}