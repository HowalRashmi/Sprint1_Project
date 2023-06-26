package com.springboot.lms.DTO;

import java.util.Date;

import javax.persistence.Id;

import com.springboot.lms.entities.Book;
import com.springboot.lms.entities.Student;

//import com.springboot.lms.entities.Book;
//import com.springboot.lms.entities.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookIssueDto {

	@Id
	private int bid;
	
	private Date bissuedate;
	
	private Date breturndate;
	
	private String breturnstatus;
	
	private Student stud;
	
	private Book book;
	
}
