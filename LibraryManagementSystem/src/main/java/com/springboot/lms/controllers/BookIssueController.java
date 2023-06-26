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


import com.springboot.lms.DTO.BookIssueDto;
import com.springboot.lms.services.BookIssueService;


@RestController
@RequestMapping("/BookIssue")
@CrossOrigin(origins = "http://localhost:4200")
public class BookIssueController {
	@Autowired private BookIssueService bissueservice;
	
	//get all data through mapping
	@GetMapping("/getAllbissue") 
	public ResponseEntity<List<BookIssueDto>> getBookIssue()
	{
		return ResponseEntity.ok(this.bissueservice.getAllBookIssue());
	}
	
	//get the data through the book issue id
	@GetMapping("/getbissue/{id}")
	public ResponseEntity<BookIssueDto> getBook(@PathVariable("id") int id)
	{
		return ResponseEntity.ok(this.bissueservice.getById(id));
	}
	
	//adding new book issue
	@PostMapping("/addbissue")
	public ResponseEntity<String> addBookIssue(@RequestBody BookIssueDto book)
	{
		this.bissueservice.addBookIssue(book);
		return ResponseEntity.ok("Data Inserted!!");
	}
	
	//deleting a book issue record
	@DeleteMapping("/deletebissue/{id}")
	public ResponseEntity<String> deleteBookIssue(@PathVariable("id") int id)
	{
		this.bissueservice.deleteBookIssue(id);
		return ResponseEntity.ok("Data Deleted!!");
	}
	
	//updating a book issue record
	@PutMapping("/updatebissue/{id}")
	public ResponseEntity<String> updateBookIssue(@RequestBody BookIssueDto book, @PathVariable("id") int id)
	{
		this.bissueservice.updateBookIssue(id, book);
		return ResponseEntity.ok("Data Updated!!");
	}
}
