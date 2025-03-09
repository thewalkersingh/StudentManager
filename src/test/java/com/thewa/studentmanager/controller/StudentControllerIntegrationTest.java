package com.thewa.studentmanager.controller;

import com.thewa.studentmanager.entity.Student;
import com.thewa.studentmanager.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerIntegrationTest {
   
   @Autowired
   private MockMvc mockMvc;
   
   @Autowired
   private StudentRepository studentRepository;
   
   @Autowired
   private ObjectMapper objectMapper;
   
   @Test
   public void testValidateStudentEndpoint() throws Exception {
	  // Create a dummy student in the database
	  Student student = new Student();
	  student.setName("Test Student");
	  student.setDob(LocalDate.of(2000, 1, 1));
	  student = studentRepository.save(student);
	  
	  // Validate with correct DOB
	  mockMvc.perform(post("/api/students/validate")
							  .param("studentId", student.getStudentId().toString())
							  .param("dateOfBirth", "2000-01-01"))
			 .andExpect(status().isOk());
	  
	  // Validate with incorrect DOB
	  mockMvc.perform(post("/api/students/validate")
							  .param("studentId", student.getStudentId().toString())
							  .param("dateOfBirth", "1999-12-31"))
			 .andExpect(status().isUnauthorized());
   }
}