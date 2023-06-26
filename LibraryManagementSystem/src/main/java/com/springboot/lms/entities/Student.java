package com.springboot.lms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Student_Table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	//data members
	@Id   //primary key
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sid;
	
	//Validations
	@NotNull
	private String sName;
	
	@NotNull
	private String sClass;
	
	@NotNull
	private String sBranch;
	
	@NotNull
	private Long mobno;
	
	@Email
	private String sEmail;

	
}
