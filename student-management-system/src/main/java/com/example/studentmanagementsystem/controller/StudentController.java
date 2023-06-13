package com.example.studentmanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagementsystem.exception.StudentManagementException;
import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.services.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentService service;

	/**
	 * This method is used for creation and updation of student
	 * 
	 * @param student
	 * @return the save student object
	 * @throws StudentManagementException if student already exist with the same id
	 *                                    or rollNo
	 */
	@PostMapping("/create-student")
	public Student createStudent(@RequestBody Student student) throws StudentManagementException {
		return service.saveOrUpdateStudent(student);
	}

	/**
	 * This method is used to fetch student by it's Id from DB
	 * 
	 * @param student
	 * @return the student
	 * @throws StudentManagementException if student does't exist for the given id
	 * 
	 */
	@GetMapping("/student/{id}")
	public Optional<Student> getStudentById(@PathVariable("id") Long id) throws StudentManagementException {
		return service.getById(id);
	}

	/**
	 * This method is used to fetch all students from DB
	 * 
	 * @return list of students
	 */
	@GetMapping("/students")
	public Page<Student> getAllStudents((@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
                Pageable pageable = PageRequest.of(page, size);
		return service.fetchAllStudent(pageable);
	}
}
