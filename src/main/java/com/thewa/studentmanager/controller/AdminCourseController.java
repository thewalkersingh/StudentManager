package com.thewa.studentmanager.controller;
import com.thewa.studentmanager.dto.CourseDTO;
import com.thewa.studentmanager.dto.StudentDTO;
import com.thewa.studentmanager.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/courses")
public class AdminCourseController {
   private final CourseService courseService;
   
   public AdminCourseController(CourseService courseService) {
	  this.courseService = courseService;
   }
   
   // Upload a new course
   @PostMapping("/upload")
   public ResponseEntity<CourseDTO> uploadCourse(@Valid @RequestBody CourseDTO courseDTO) {
	  CourseDTO savedCourse = courseService.uploadCourse(courseDTO);
	  return ResponseEntity.ok(savedCourse);
   }
   
   // Get all courses
   @GetMapping
   public ResponseEntity<List<CourseDTO>> getAllCourses() {
	  List<CourseDTO> courses = courseService.getAllCourses();
	  return ResponseEntity.ok(courses);
   }
   
   // Get students enrolled in a course
   @GetMapping("/{courseId}/students")
   public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable Long courseId) {
	  List<StudentDTO> students = courseService.getStudentsByCourseId(courseId);
	  return ResponseEntity.ok(students);
   }
}