package com.springboot.lms.converter;

import java.util.List;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.lms.DTO.BookDto;
import com.springboot.lms.entities.Book;

@Component
public class BookConverter {

	//converting entity data to dto objects
	public BookDto entityToDto(Book book)
	{
		BookDto bdto = new BookDto();
		bdto.setBid(book.getBid());
		bdto.setBname(book.getBname());
		bdto.setBcategory(book.getBcategory());
		bdto.setAuthor(book.getAuthor());
		return bdto;
	}
	
	//converting entity data to dto object list
	public List<BookDto> entityToDtoList(List<Book> book)
	{
		return book.stream().map(x-> entityToDto(x)).collect(Collectors.toList());				
	}
	
	//converting dto objects to entity data
	public Book dtoToEntity(BookDto bdto)
	{
		Book b= new Book();
		b.setBid(bdto.getBid());
		b.setBname(bdto.getBname());
		b.setBcategory(bdto.getBcategory());
		b.setAuthor(bdto.getAuthor());
		return b;
	}
	
	//converting dto objects to entity data list
	public List<Book> dtoToEntityList(List<BookDto> bdto)
	{
		return bdto.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
	}
}
