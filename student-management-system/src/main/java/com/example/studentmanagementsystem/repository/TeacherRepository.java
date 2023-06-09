package com.example.studentmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagementsystem.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	boolean existsByEmail(String email);
	Teacher findByEmail(String email);
    Optional<Teacher> findByUsernameOrEmail(String username, String email);
    Optional<Teacher> findByUsername(String username);
    Boolean existsByUsername(String username);

}
