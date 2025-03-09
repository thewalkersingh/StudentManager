package com.thewa.studentmanager.controller;
import com.thewa.studentmanager.dto.StudentDTO;
import com.thewa.studentmanager.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/students")
public class AdminStudentController {
   
   private final StudentService studentService;
   
   public AdminStudentController(StudentService studentService) {
	  this.studentService = studentService;
   }
   
   // Admit a new student
   @PostMapping("/admit")
   public ResponseEntity<StudentDTO> admitStudent(@Valid @RequestBody StudentDTO studentDTO) {
	  StudentDTO savedStudent = studentService.admitStudent(studentDTO);
	  return ResponseEntity.ok(savedStudent);
   }
   
   // Search students by name
   @GetMapping("/search")
   public ResponseEntity<List<StudentDTO>> searchStudents(@RequestParam String name) {
	  List<StudentDTO> students = studentService.getStudentsByName(name);
	  return ResponseEntity.ok(students);
   }
   
   // Assign course to a student
   @PutMapping("/{studentId}/courses/{courseId}")
   public ResponseEntity<StudentDTO> assignCourse(@PathVariable Long studentId,
		   @PathVariable Long courseId) {
	  StudentDTO updatedStudent = studentService.assignCourse(studentId, courseId);
	  return ResponseEntity.ok(updatedStudent);
   }
}