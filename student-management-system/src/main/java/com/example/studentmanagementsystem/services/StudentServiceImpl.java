package com.example.studentmanagementsystem.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagementsystem.exception.StudentManagementException;
import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository repo;
	
	
	@Override
	@Transactional
	public Student saveOrUpdateStudent(Student student) throws StudentManagementException{
		if(student.getId() != null) {
			return updateStudent(student);
		}
		return createStudent(student);
	}


	private Student createStudent(Student student) throws StudentManagementException{
		Student newStudent = repo.findByRollNo(student.getRollNo());
		if (student != null) {
			throw new StudentManagementException("Student already existing with this roll no.");
		}
		return repo.save(newStudent);
	}


	private Student updateStudent(Student student) throws StudentManagementException {
		Optional<Student> studentById = repo.findById(student.getId());
		if(null != studentById ) {
			throw new StudentManagementException("Student already existing with this Id.");
		}
		Student newStudent = repo.findByRollNo(student.getRollNo());
		if (null != student) {
			throw new StudentManagementException("Student already existing with this roll no.");
		}
		return repo.save(newStudent);
	}


	@Override
	public Optional<Student> getById(Long id) throws StudentManagementException {
		Optional<Student> student= repo.findById(id);
		if(null == student) {
			throw new StudentManagementException("Student not found with given id.");
		}
		return repo.findById(id);
	}


	@Override
	public List<Student> fetchAllStudent() {
		return repo.findAll();
	}
	
}
