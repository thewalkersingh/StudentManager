package com.thewa.studentmanager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thewa.studentmanager.dto.CourseDTO;
import com.thewa.studentmanager.dto.StudentDTO;
import com.thewa.studentmanager.entity.Course;
import com.thewa.studentmanager.entity.Student;
import com.thewa.studentmanager.entity.StudentAddress;
import com.thewa.studentmanager.mapper.CourseMapper;
import com.thewa.studentmanager.mapper.StudentMapper;
import com.thewa.studentmanager.repository.CourseRepository;
import com.thewa.studentmanager.repository.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	private final StudentMapper studentMapper;
	private final CourseMapper courseMapper;

	public StudentService(StudentMapper studentMapper, StudentRepository studentRepository,
			CourseRepository courseRepository, CourseMapper courseMapper) {
		this.studentMapper = studentMapper;
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.courseMapper = courseMapper;
	}

	// Admit a new student (Admin feature)
	public StudentDTO admitStudent(StudentDTO studentDTO) {
		Student student = studentMapper.toEntity(studentDTO);
		Student saved = studentRepository.save(student);
		return studentMapper.toDTO(saved);
	}

	// Assign course to a student (Admin feature)
	public StudentDTO assignCourse(Long studentId, Long courseId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
		student.getCourses().add(course);
		course.getStudents().add(student);
		studentRepository.save(student);
		courseRepository.save(course);
		return studentMapper.toDTO(student);
	}

	// Search for courses assigned to a student with an optional search keyword.
	public List<CourseDTO> getAssignedCourses(Long studentId, String search) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));
		Set<Course> courses = student.getCourses();

		if (search != null && !search.isEmpty()) {
			courses = courses.stream().filter(course -> course.getCourseName().toLowerCase()
					.contains(search.toLowerCase())
					|| (course.getTopics() != null && course.getTopics().toLowerCase().contains(search.toLowerCase())))
					.collect(Collectors.toSet());
		}

		return courses.stream().map(courseMapper::toDTO).collect(Collectors.toList());
	}

	// Search students by name (Admin feature)
	public List<StudentDTO> getStudentsByName(String name) {
		return studentRepository.findByNameContaining(name).stream().map(studentMapper::toDTO)
				.collect(Collectors.toList());
	}

	// Student leaves a course
	public StudentDTO leaveCourse(Long studentId, Long courseId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
		if (!student.getCourses().contains(course)) {
			throw new RuntimeException("Student is not enrolled in this course");
		}
		student.getCourses().remove(course);
		course.getStudents().remove(student);
		studentRepository.save(student);
		courseRepository.save(course);
		return studentMapper.toDTO(student);
	}

	// Update student profile details
	public StudentDTO updateProfile(Long studentId, StudentDTO updateData) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));
		student.setEmail(updateData.getEmail());
		student.setMobile(updateData.getMobile());
		student.setParentName(updateData.getParentName());
		student.setParentNumber(updateData.getParentNumber());
		if (updateData.getAddresses() != null && !updateData.getAddresses().isEmpty()) {
			student.getAddresses().clear();
			updateData.getAddresses().forEach(addressDTO -> {
				StudentAddress address = studentMapper.toAddressEntity(addressDTO, student);
				address.setStudent(student);
				student.getAddresses().add(address);
			});
		}

		Student updated = studentRepository.save(student);
		return studentMapper.toDTO(updated);
	}

	// Validate student credentials using studentId and date of birth
	public boolean validateStudent(Long studentId, LocalDate dateOfBirth) {
		Optional<Student> studentOpt = studentRepository.findById(studentId);
		return studentOpt.isPresent() && studentOpt.get().getDob().equals(dateOfBirth);
	}
}