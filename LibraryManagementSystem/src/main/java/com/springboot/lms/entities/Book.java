package com.springboot.lms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Book_Table")
public class Book {
	
	//Data Members
	@Id  //primary key
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bid;
	
	@NotNull
	private String bname;
	
	@NotNull
	private String bcategory;
	
	@NotNull
	private String author;
	
}
