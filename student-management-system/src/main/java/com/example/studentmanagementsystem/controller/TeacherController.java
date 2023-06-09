package com.example.studentmanagementsystem.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagementsystem.Dto.LoginDto;
import com.example.studentmanagementsystem.Dto.SignUpDto;
import com.example.studentmanagementsystem.model.Role;
import com.example.studentmanagementsystem.model.Teacher;
import com.example.studentmanagementsystem.repository.RoleRepository;
import com.example.studentmanagementsystem.repository.TeacherRepository;

@RestController
@RequestMapping("/api/auth")
public class TeacherController {
	
	@Autowired
	TeacherRepository repo;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
	
	/**
	 * This method is used for sign up for teacher
	 * 
	 * @param signUpDto is the dto object of sign up for teacher
	 * @return the success or exception while sign up process
	 */
	@PostMapping("/signup")
    public ResponseEntity<?> signUpTeacher(@RequestBody SignUpDto signUpDto){

        if(repo.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if(repo.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("This Email is already existing", HttpStatus.BAD_REQUEST);
        }

        // create user object
        Teacher teacher = new Teacher();
        teacher.setName(signUpDto.getName());
        teacher.setUsername(signUpDto.getUsername());
        teacher.setEmail(signUpDto.getEmail());
        teacher.setAge(signUpDto.getAge());
        teacher.setGender(signUpDto.getGender());
        teacher.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        teacher.setRoles(Collections.singleton(roles));

        repo.save(teacher);

        return new ResponseEntity<>("User sign-up successfully", HttpStatus.OK);
    }
	
	/**
	 * This method is used for login
	 * 
	 * @param loginDto is the dto object for login
	 * @return the success or exception while sign up process
	 */
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
}
