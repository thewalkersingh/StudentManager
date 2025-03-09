package com.thewa.studentmanager.controller;
import com.thewa.studentmanager.dto.CourseDTO;
import com.thewa.studentmanager.dto.StudentDTO;
import com.thewa.studentmanager.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student - Self Service", description = "Endpoints for student self-service features")
public class StudentController {
   private final StudentService studentService;
   
   public StudentController(StudentService studentService) {
	  this.studentService = studentService;
   }
   
   /**
	* Validate student credentials.
	*
	* @param studentId   the student's ID
	* @param dateOfBirth the student's date of birth
	* @return a success message if valid; otherwise, an unauthorized status
	*/
   @Operation(summary = "Validate Credentials",
			  description = "Validates a student's credentials using their ID and date of birth.")
   @PostMapping("/validate")
   public ResponseEntity<String> validateStudent(
		   @RequestParam Long studentId,
		   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {
	  boolean valid = studentService.validateStudent(studentId, dateOfBirth);
	  if (valid)
		 return ResponseEntity.ok("Student validated successfully!");
	  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
   }
   
   /**
	* Update student profile.
	*
	* @param studentId  the student's ID
	* @param updateData the updated student profile details
	* @return the updated student details
	*/
   @Operation(summary = "Update Profile",
			  description = "Allows a student to update their profile details including email, mobile number, " +
							"parent's details, and addresses.")
   @PutMapping("/update/{studentId}")
   public ResponseEntity<StudentDTO> updateProfile(
		   @PathVariable Long studentId,
		   @Valid @RequestBody StudentDTO updateData) {
	  StudentDTO updatedStudent = studentService.updateProfile(studentId, updateData);
	  return ResponseEntity.ok(updatedStudent);
   }
   
   /**
	* Get courses assigned to the student.
	*
	* @param studentId the student's ID
	* @param search    optional search term to filter courses
	* @return a list of courses assigned to the student
	*/
   @Operation(summary = "Get Assigned Courses",
			  description =
					  "Retrieves a list of courses assigned to the student, optionally filtered by a search" +
					  " term.")
   @GetMapping("/courses")
   public ResponseEntity<List<CourseDTO>> getAssignedCourses(
		   @RequestParam Long studentId,
		   @RequestParam(required = false) String search) {
	  List<CourseDTO> courses = studentService.getAssignedCourses(studentId, search);
	  return ResponseEntity.ok(courses);
   }
   
   /**
	* Leave a course.
	*
	* @param courseId  the course ID to leave
	* @param studentId the student's ID
	* @return the updated student details after leaving the course
	*/
   @Operation(summary = "Leave Course", description = "Allows a student to leave a course.")
   @PostMapping("/leave/{courseId}")
   public ResponseEntity<StudentDTO> leaveCourse(
		   @PathVariable Long courseId,
		   @RequestParam Long studentId) {
	  StudentDTO updatedStudent = studentService.leaveCourse(studentId, courseId);
	  return ResponseEntity.ok(updatedStudent);
   }
}