package com.thewa.studentmanager.mapper;
import com.thewa.studentmanager.dto.CourseDTO;
import com.thewa.studentmanager.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
   @Mapping(target = "courseId", ignore = true)
   @Mapping(target = "students", ignore = true)
   Course toEntity(CourseDTO courseDTO);
   
   CourseDTO toDTO(Course course);
}