package com.thewa.studentmanager.repository;
import com.thewa.studentmanager.entity.StudentAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long> {
}