package com.example.studentmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentmanagementsystem.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	public Optional<Student> findById(Long id); 
	
	public List<Student> findAll();
	
    Student findByRollNo(String rollNo);
}
