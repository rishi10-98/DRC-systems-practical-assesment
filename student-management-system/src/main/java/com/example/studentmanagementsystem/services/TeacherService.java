package com.example.studentmanagementsystem.services;


import org.springframework.stereotype.Service;

import com.example.studentmanagementsystem.model.Teacher;

@Service
public interface TeacherService {
	
	Teacher createTeacher(Teacher teacher);

	boolean getByEmailId(String email);
	


}
