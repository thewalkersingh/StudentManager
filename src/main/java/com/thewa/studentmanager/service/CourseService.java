package com.thewa.studentmanager.service;
import com.thewa.studentmanager.dto.CourseDTO;
import com.thewa.studentmanager.dto.StudentDTO;
import com.thewa.studentmanager.entity.Course;
import com.thewa.studentmanager.entity.Student;
import com.thewa.studentmanager.mapper.CourseMapper;
import com.thewa.studentmanager.mapper.StudentMapper;
import com.thewa.studentmanager.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {
   private final CourseRepository courseRepository;
   private final CourseMapper courseMapper;
   private final StudentMapper studentMapper;
   
   public CourseService(
		   CourseRepository courseRepository,
		   CourseMapper courseMapper,
		   StudentMapper studentMapper) {
	  this.courseRepository = courseRepository;
	  this.courseMapper = courseMapper;
	  this.studentMapper = studentMapper;
   }
   
   // Upload a course (Admin feature)
   public CourseDTO uploadCourse(CourseDTO courseDTO) {
	  Course course = courseMapper.toEntity(courseDTO);
	  Course saved = courseRepository.save(course);
	  return courseMapper.toDTO(saved);
   }
   
   // Get all courses (Admin feature)
   public List<CourseDTO> getAllCourses() {
	  return courseRepository.findAll()
							 .stream()
							 .map(courseMapper::toDTO)
							 .collect(Collectors.toList());
   }
   
   // Get students by course (Admin feature)
   public List<StudentDTO> getStudentsByCourseId(Long courseId) {
	  Course course = courseRepository.findById(courseId)
									  .orElseThrow(() -> new RuntimeException("Course not found"));
	  Set<Student> students = new HashSet<>(course.getStudents());
	  return students.stream()
					 .map(studentMapper::toDTO)
					 .collect(Collectors.toList());
   }
}