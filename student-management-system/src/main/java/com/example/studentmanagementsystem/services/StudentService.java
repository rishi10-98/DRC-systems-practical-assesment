package com.example.studentmanagementsystem.services;

import java.util.List;
import java.util.Optional;

import com.example.studentmanagementsystem.exception.StudentManagementException;
import com.example.studentmanagementsystem.model.Student;


public interface StudentService {

	Student saveOrUpdateStudent(Student student) throws StudentManagementException;

	Optional<Student> getById(Long id) throws StudentManagementException;

	List<Student> fetchAllStudent();
}
