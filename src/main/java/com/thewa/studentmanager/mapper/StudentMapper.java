package com.thewa.studentmanager.mapper;
import com.thewa.studentmanager.dto.StudentAddressDTO;
import com.thewa.studentmanager.dto.StudentDTO;
import com.thewa.studentmanager.entity.Student;
import com.thewa.studentmanager.entity.StudentAddress;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {
   @Mapping(target = "studentId", ignore = true)
   @Mapping(target = "courses", ignore = true)
   Student toEntity(StudentDTO studentDTO);
   
   StudentDTO toDTO(Student student);
   
   default StudentAddress toAddressEntity(StudentAddressDTO addressDTO, Student student) {
	  StudentAddress address = new StudentAddress();
	  address.setArea(addressDTO.getArea());
	  address.setState(addressDTO.getState());
	  address.setDistrict(addressDTO.getDistrict());
	  address.setPincode(addressDTO.getPincode());
	  address.setAddressType(addressDTO.getAddressType());
	  address.setStudent(student);
	  return address;
   }
   
   @AfterMapping
   default void assignAddresses(StudentDTO studentDTO, @MappingTarget Student student) {
	  if (studentDTO.getAddresses() != null) {
		 studentDTO
				 .getAddresses()
				 .forEach(addressDTO -> student.getAddresses().add(toAddressEntity(addressDTO, student)));
	  }
   }
}