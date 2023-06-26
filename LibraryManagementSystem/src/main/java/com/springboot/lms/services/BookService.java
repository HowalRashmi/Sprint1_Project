package com.springboot.lms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lms.DAO.BookRepository;
import com.springboot.lms.DTO.BookDto;
import com.springboot.lms.converter.BookConverter;
import com.springboot.lms.entities.Book;
import com.springboot.lms.exceptions.BookNotFound;
import com.springboot.lms.exceptions.InsertBookException;

@Service
public class BookService {
	@Autowired private BookRepository bookrepo;
	@Autowired private BookConverter bdto;
	
	//getting all books from the database
	public List<BookDto> getAllBooks()
	{
		List<Book> book = (List<Book>)this.bookrepo.findAll();
			return bdto.entityToDtoList(book);
	}
	
	//getting a particular book based on book id
	public BookDto getById(int bid) throws BookNotFound
	{
		Book book= this.bookrepo.findById(bid); 
		if(book!= null)
		{ 
			return this.bdto.entityToDto(book);
		}
		else
		{			
			throw new BookNotFound();			
		}
		
	}

	//adding a new book into the database
	public BookDto addBook(BookDto b) throws InsertBookException
	{
		Book book = this.bdto.dtoToEntity(b);
		Book result = this.bookrepo.save(book); // save into the data table.
		return this.bdto.entityToDto(result);
	}	
	
	//deleting a book record from the database
	public void deleteBook(int bid) throws BookNotFound
	{
		this.bookrepo.deleteById(bid);
	}
	
	//updating a book record
	public BookDto updateBook(int bid, BookDto bdto) throws BookNotFound
	{
					
			Book b1 = bookrepo.findById(bid);
			b1.setBname(bdto.getBname());
			b1.setBcategory(bdto.getBcategory());
			b1.setAuthor(bdto.getAuthor());
			bookrepo.save(b1);
			return this.bdto.entityToDto(b1);
	}
}
