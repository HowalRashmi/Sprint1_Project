package com.springboot.lms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Book_Issue")
public class BookIssue {

	//Data Members
	@Id  //primary key
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private Date issueDate;
	
	private Date returnDate;
	
	private String returnStatus;
	
	@OneToOne
	@JsonManagedReference
	private Student stud;
	
	@OneToOne
	@JsonManagedReference
	private Book book;
	
	
}
