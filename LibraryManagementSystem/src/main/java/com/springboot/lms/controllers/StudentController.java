package com.springboot.lms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.DTO.StudentDto;
import com.springboot.lms.services.StudentService;

@RestController
@RequestMapping("/Student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	@Autowired private StudentService studservice;
	
	//get all data through mapping
	@GetMapping("/getAllstud") 
	public ResponseEntity<List<StudentDto>> getStudents()
	{		
		return ResponseEntity.ok(this.studservice.getAllStudents());
	}
	
	
	//get the data through the student id
	@GetMapping("/getstud/{sid}")
	public ResponseEntity<StudentDto> getStud(@PathVariable("sid") int id)
	{
		return ResponseEntity.ok(this.studservice.getById(id));
	}
	

	//adding new student data 
	@PostMapping("/addstud")
	public ResponseEntity<String> addStud(@RequestBody StudentDto stud)
	{
		this.studservice.addStud(stud);
		return ResponseEntity.ok("Student Data Inserted!!");
	}
	
	
	//deleting a student record
	@DeleteMapping("/deletestud/{sid}")
	public ResponseEntity<String> deleteStud(@PathVariable("sid") int id)
	{
		this.studservice.deleteStudent(id);
		return ResponseEntity.ok("Student Data Deleted!!");
	}
	
	
	//updating a student record
	@PutMapping("/updatestud/{sid}")
	public ResponseEntity<String> updateStud(@RequestBody StudentDto stud, @PathVariable("sid") int id)
	{
		this.studservice.updateStud(id, stud);
		return ResponseEntity.ok("Student Data Updated!!");
	}
}
