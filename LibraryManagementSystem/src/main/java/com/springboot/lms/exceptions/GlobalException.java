package com.springboot.lms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	    //Exception handling for book
		@ExceptionHandler(value = BookNotFound.class)
		public ResponseEntity<?> BookNotFoundException(){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		@ExceptionHandler(value = InsertBookException.class)
		public ResponseEntity<?> AddBookException(){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
		 
		
		//Exception handling for student		
		@ExceptionHandler(value = StudentNotFound.class)
		public ResponseEntity<?> StudentNotFoundException(){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		@ExceptionHandler(value = InsertStudentException.class)
		public ResponseEntity<?> AddStudentException(){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
		
		
		//Exception Handling for Book Issue
		@ExceptionHandler(value = BookIssueNotFound.class)
		public ResponseEntity<?> BookIssueNotFoundException(){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		@ExceptionHandler(value = InsertBookIssueException.class)
		public ResponseEntity<?> AddBookIssueException(){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
}
