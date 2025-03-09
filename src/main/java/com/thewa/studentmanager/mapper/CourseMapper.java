package com.thewa.studentmanager.mapper;
import com.thewa.studentmanager.dto.CourseDTO;
import com.thewa.studentmanager.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
   Course toEntity(CourseDTO courseDTO);
   
   CourseDTO toDTO(Course course);
}