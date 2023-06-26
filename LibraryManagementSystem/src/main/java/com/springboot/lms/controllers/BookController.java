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

import com.springboot.lms.DTO.BookDto;
import com.springboot.lms.services.BookService;


@RestController
@RequestMapping("/Book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
	@Autowired private BookService bookservice;
	
	//get all data through mapping
	@GetMapping("/getAllbooks") 
	public ResponseEntity<List<BookDto>> getBooks()
	{
		return ResponseEntity.ok(this.bookservice.getAllBooks());
	}
	
	
	//get the book data through the book id
	@GetMapping("/getbook/{id}")
	public ResponseEntity<BookDto> getBook(@PathVariable("id") int id)
	{
		return ResponseEntity.ok(this.bookservice.getById(id));
	}
	
	
	//adding new book record
	@PostMapping("/addbook")
	public ResponseEntity<String> addBook(@RequestBody BookDto book)
	{
		this.bookservice.addBook(book);
		return ResponseEntity.ok("insert");
	}
	
	
	//deleting a book record
	@DeleteMapping("/deletebook/{bookid}")
	public ResponseEntity<String> deleteBook(@PathVariable("bookid") int id)
	{
		this.bookservice.deleteBook(id);
		return ResponseEntity.ok("delete");
	}
	
	
	//updating a book record
	@PutMapping("/updatebook/{bookid}")
	public ResponseEntity<String> updateBook(@RequestBody BookDto book, @PathVariable("bookid") int id)
	{
		this.bookservice.updateBook(id, book);
		return ResponseEntity.ok("updated");
	}
}
