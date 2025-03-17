package com.thewa.studentmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thewa.studentmanager.dto.CourseDTO;
import com.thewa.studentmanager.dto.StudentDTO;
import com.thewa.studentmanager.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/courses")
@Tag(name = "Admin - Course Management", description = "Endpoints for managing courses (admin only)")
public class AdminCourseController {
    private final CourseService courseService;

    public AdminCourseController(CourseService courseService) {
	this.courseService = courseService;
    }

    /**
     * Upload a new course.
     *
     * @param courseDTO the course details to upload
     * @return the saved course details with generated IDs
     */
    @Operation(summary = "Upload Course", description = "Uploads new course details for administration.")
    @PostMapping("/upload")
    public ResponseEntity<CourseDTO> uploadCourse(@Valid @RequestBody CourseDTO courseDTO) {
	CourseDTO savedCourse = courseService.uploadCourse(courseDTO);
	return ResponseEntity.ok(savedCourse);
    }

    /**
     * Get all courses.
     *
     * @return a list of all courses
     */
    @Operation(summary = "Get All Courses", description = "Retrieves a list of all available courses.")
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
	List<CourseDTO> courses = courseService.getAllCourses();
	return ResponseEntity.ok(courses);
    }

    /**
     * Get students enrolled in a specific course.
     *
     * @param courseId the ID of the course
     * @return a list of students enrolled in that course
     */
    @Operation(summary = "Get Students By Course", description = "Retrieves a list of students enrolled in the specified course.")
    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable Long courseId) {
	List<StudentDTO> students = courseService.getStudentsByCourseId(courseId);
	return ResponseEntity.ok(students);
    }
}