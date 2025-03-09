package com.thewa.studentmanager.controller;
import com.thewa.studentmanager.dto.CourseDTO;
import com.thewa.studentmanager.dto.StudentDTO;
import com.thewa.studentmanager.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
   private final StudentService studentService;
   
   public StudentController(StudentService studentService) {
	  this.studentService = studentService;
   }
   
   // Validate student credentials using studentId and dateOfBirth
   @PostMapping("/validate")
   public ResponseEntity<String> validateStudent(
		   @RequestParam Long studentId,
		   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {
	  boolean valid = studentService.validateStudent(studentId, dateOfBirth);
	  if (valid)
		 return ResponseEntity.ok("Student validated successfully!");
	  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
   }
   
   // Update profile details
   @PutMapping("/update/{studentId}")
   public ResponseEntity<StudentDTO> updateProfile(
		   @PathVariable Long studentId,
		   @Valid @RequestBody StudentDTO updateData) {
	  StudentDTO updatedStudent = studentService.updateProfile(studentId, updateData);
	  return ResponseEntity.ok(updatedStudent);
   }
   
   // Get courses assigned to the student (with optional search filter)
   @GetMapping("/courses")
   public ResponseEntity<List<CourseDTO>> getAssignedCourses(
		   @RequestParam Long studentId,
		   @RequestParam(required = false) String search) {
	  List<CourseDTO> courses = studentService.getAssignedCourses(studentId, search);
	  return ResponseEntity.ok(courses);
   }
   
   // Leave a course (existing feature)
   @PostMapping("/leave/{courseId}")
   public ResponseEntity<StudentDTO> leaveCourse(
		   @PathVariable Long courseId,
		   @RequestParam Long studentId) {
	  StudentDTO updatedStudent = studentService.leaveCourse(studentId, courseId);
	  return ResponseEntity.ok(updatedStudent);
   }
}