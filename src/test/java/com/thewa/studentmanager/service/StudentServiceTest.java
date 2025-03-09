package com.thewa.studentmanager.service;
import com.thewa.studentmanager.entity.Student;
import com.thewa.studentmanager.mapper.CourseMapper;
import com.thewa.studentmanager.mapper.StudentMapper;
import com.thewa.studentmanager.repository.CourseRepository;
import com.thewa.studentmanager.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
   @Mock
   private StudentRepository studentRepository;
   
   @Mock
   private CourseRepository courseRepository;
   
   @Mock
   private StudentMapper studentMapper;
   
   @Mock
   private CourseMapper courseMapper;
   
   @InjectMocks
   private StudentService studentService;
   
   @Test
   public void testValidateStudent_Success() {
	  Long studentId = 1L;
	  LocalDate dob = LocalDate.of(2000, 1, 1);
	  Student student = new Student();
	  student.setDob(dob);
	  
	  when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
	  
	  boolean result = studentService.validateStudent(studentId, dob);
	  assertTrue(result);
   }
   
   @Test
   public void testValidateStudent_Failure() {
	  Long studentId = 1L;
	  LocalDate dob = LocalDate.of(2000, 1, 1);
	  
	  when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
	  
	  boolean result = studentService.validateStudent(studentId, dob);
	  assertFalse(result);
   }
}