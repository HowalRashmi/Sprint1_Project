package com.springboot.lms.DTO;

import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

	@Id
	private int bid;
	
	private String bname;
	
	private String bcategory;
	
	private String author;
	


}
