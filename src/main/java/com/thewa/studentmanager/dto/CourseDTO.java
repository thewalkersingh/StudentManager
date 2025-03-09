package com.thewa.studentmanager.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseDTO {
   @NotBlank(message = "Course name is required")
   private String courseName;
   private String description;
   private String courseType;
   private String duration;
   private String topics;
}