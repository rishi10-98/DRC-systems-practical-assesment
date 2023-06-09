package com.example.studentmanagementsystem.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.studentmanagementsystem.model.Teacher;
import com.example.studentmanagementsystem.repository.TeacherRepository;

@Service
public class CustomTeacherDetailsService implements UserDetailsService{
   
   private TeacherRepository repo;

   public CustomTeacherDetailsService(TeacherRepository repo) {
       this.repo = repo;
   }
   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Teacher teacher = repo.findByEmail(username);
		if (teacher == null) {
			throw new UsernameNotFoundException("User not found for email" + username);
		}
		return new org.springframework.security.core.userdetails.User(teacher.getEmail(), teacher.getPassword(),
				(Collection<? extends GrantedAuthority>) teacher.getRoles());
	}

}
