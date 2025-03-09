package com.thewa.studentmanager.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentDTO {
   @NotBlank(message = "Name is required")
   private String name;
   
   @NotNull(message = "Date of birth is required")
   private LocalDate dob;
   
   @NotBlank(message = "Gender is required")
   private String gender;
   private String email;
   private String mobile;
   
   @NotBlank(message = "Parent's name is required")
   private String parentName;
   private String parentNumber;
   private List<StudentAddressDTO> addresses;
}