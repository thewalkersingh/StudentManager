package com.thewa.studentmanager.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentAddress {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long addressId;
   private String area;
   private String state;
   private String district;
   private String pincode;
   private String addressType;
   
   @ManyToOne
   @JoinColumn(name = "student_id", nullable = false)
   private Student student;
}