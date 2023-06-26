package com.springboot.lms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	public Student findById(int sid);
}
