package com.thewa.studentmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thewa.studentmanager.dto.StudentDTO;
import com.thewa.studentmanager.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/students")
@Tag(name = "Admin - Student Management", description = "Endpoints for managing students (admin only)")
public class AdminStudentController {
	private final StudentService studentService;

	public AdminStudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * Admit new student.
	 *
	 * @param studentDTO the student details to be admitted
	 * @return the admitted student details with generated IDs
	 */
	@Operation(summary = "Admit a New Student", description = "Admits a new student with personal details and addresses.")

	@PostMapping("/admit")
	public ResponseEntity<StudentDTO> admitStudent(@Valid @RequestBody StudentDTO studentDTO) {
		StudentDTO savedStudent = studentService.admitStudent(studentDTO);
		return ResponseEntity.ok(savedStudent);
	}

	/**
	 * Searching the students by name
	 *
	 * @param name partial or full name to search by
	 * @return a list of students matching the search criteria
	 */
	@Operation(summary = "Search Students", description = "Searches for students by name.")
	@GetMapping("/search")
	public ResponseEntity<List<StudentDTO>> searchStudents(@RequestParam String name) {
		List<StudentDTO> students = studentService.getStudentsByName(name);
		return ResponseEntity.ok(students);
	}

	/**
	 * Assign a course to a student
	 *
	 * @param studentId the ID of the student
	 * @param courseId  the ID of the course to assign
	 * @return the updated student details with the course added
	 */
	@Operation(summary = "Assign Course", description = "Assigns a course to a student using their IDs.")
	@PutMapping("/{studentId}/courses/{courseId}")
	public ResponseEntity<StudentDTO> assignCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
		StudentDTO updatedStudent = studentService.assignCourse(studentId, courseId);
		return ResponseEntity.ok(updatedStudent);
	}
}