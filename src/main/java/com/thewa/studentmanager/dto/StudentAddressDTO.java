package com.thewa.studentmanager.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentAddressDTO {
   private String area;
   private String state;
   private String district;
   private String pincode;
   private String addressType;
}