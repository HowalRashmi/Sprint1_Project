package com.springboot.lms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	public Book findById(int bid);

}
