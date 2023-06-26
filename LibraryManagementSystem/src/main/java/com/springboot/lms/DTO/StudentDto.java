package com.springboot.lms.DTO;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	@Id
	private int sid;
	
	private String sname;
	
	private String sclass;
	
	private String sbranch;
	
	private Long mobno;
	
	private String semail;

	
}
