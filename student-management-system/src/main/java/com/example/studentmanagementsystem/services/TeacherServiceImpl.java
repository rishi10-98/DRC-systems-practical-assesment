package com.example.studentmanagementsystem.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagementsystem.model.Teacher;
import com.example.studentmanagementsystem.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository repo;
	
	@Override
	public Teacher createTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return repo.save(teacher);
	}

	@Override
	public boolean getByEmailId(String email) {
		// TODO Auto-generated method stub
		return repo.existsByEmail(email);
	}



}
