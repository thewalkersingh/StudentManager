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

   @Override
   public void run(String... args) throws Exception {
	  // Create Courses
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

	  // Create Student 1: John Doe
	  Student student1 = new Student();
	  student1.setName("John Doe");
	  student1.setDob(LocalDate.of(2000, 1, 1));
	  student1.setGender("Male");
	  student1.setEmail("john.doe@example.com");
	  student1.setMobile("1234567890");
	  student1.setParentName("Jane Doe");
	  student1.setParentNumber("0987654321");

	  StudentAddress address1 = new StudentAddress();
	  address1.setArea("Downtown");
	  address1.setState("California");
	  address1.setDistrict("Los Angeles");
	  address1.setPincode("90001");
	  address1.setAddressType("Permanent");
	  address1.setStudent(student1);
	  student1.getAddresses().add(address1);

	  // Assign courses to John Doe
	  student1.getCourses().add(javaCourse);
	  student1.getCourses().add(springCourse);
	  studentRepository.save(student1);

	  // Create Student 2: Alice Smith
	  Student student2 = new Student();
	  student2.setName("Alice Smith");
	  student2.setDob(LocalDate.of(1999, 5, 15));
	  student2.setGender("Female");
	  student2.setEmail("alice.smith@example.com");
	  student2.setMobile("1112223333");
	  student2.setParentName("Robert Smith");
	  student2.setParentNumber("4445556666");

	  StudentAddress address2 = new StudentAddress();
	  address2.setArea("Midtown");
	  address2.setState("New York");
	  address2.setDistrict("New York City");
	  address2.setPincode("10001");
	  address2.setAddressType("Correspondence");
	  address2.setStudent(student2);
	  student2.getAddresses().add(address2);

	  // Assign courses to Alice Smith
	  student2.getCourses().add(springCourse);
	  student2.getCourses().add(reactCourse);
	  studentRepository.save(student2);

	  // Create Student 3: Bob Johnson
	  Student student3 = new Student();
	  student3.setName("Bob Johnson");
	  student3.setDob(LocalDate.of(2001, 8, 20));
	  student3.setGender("Male");
	  student3.setEmail("bob.johnson@example.com");
	  student3.setMobile("2223334444");
	  student3.setParentName("Susan Johnson");
	  student3.setParentNumber("7778889999");

	  StudentAddress address3 = new StudentAddress();
	  address3.setArea("Uptown");
	  address3.setState("Texas");
	  address3.setDistrict("Houston");
	  address3.setPincode("77001");
	  address3.setAddressType("Current");
	  address3.setStudent(student3);
	  student3.getAddresses().add(address3);

	  // Assign courses to Bob Johnson
	  student3.getCourses().add(javaCourse);
	  student3.getCourses().add(reactCourse);
	  studentRepository.save(student3);
   }
}