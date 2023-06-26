package com.springboot.lms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lms.DAO.BookIssueRepository;
import com.springboot.lms.DTO.BookIssueDto;
import com.springboot.lms.converter.BookIssueConverter;
import com.springboot.lms.entities.BookIssue;
import com.springboot.lms.exceptions.BookIssueNotFound;
import com.springboot.lms.exceptions.InsertBookIssueException;


@Service
public class BookIssueService {
	@Autowired private BookIssueRepository bookissuerepo;
	@Autowired private BookIssueConverter biconverter;
	
	//getting all book issues
	public List<BookIssueDto> getAllBookIssue()
	{
		List<BookIssue> bi = (List<BookIssue>)this.bookissuerepo.findAll();
			return biconverter.entityToDtoList(bi);
	}
	
	//getting book issue details by id
	public BookIssueDto getById(int id) throws BookIssueNotFound
	{
		BookIssue bissue= this.bookissuerepo.findById(id); 
		if(bissue!= null)
		{ 
			return this.biconverter.entityToDto(bissue);
		}
		else
		{			
			throw new BookIssueNotFound();			
		}
		
	}
	
	//adding a new book issue
	public BookIssueDto addBookIssue(BookIssueDto b) throws InsertBookIssueException
	{
		BookIssue book = this.biconverter.dtoToEntity(b);
		BookIssue result = this.bookissuerepo.save(book); // save into the data table.
		return this.biconverter.entityToDto(result);
	}	
	
	//deleting a book issue when the book is returned by the student
	public void deleteBookIssue(int id)
	{
		this.bookissuerepo.deleteById(id);
	}
	
	//updating a book issue if the student has returned or renewed the book
	public BookIssueDto updateBookIssue(int id, BookIssueDto bdto)
	{
			BookIssue b1=bookissuerepo.findById(id);
			b1.setIssueDate(bdto.getBissuedate());
			b1.setReturnDate(bdto.getBreturndate());
			b1.setReturnStatus(bdto.getBreturnstatus());
			bookissuerepo.save(b1);
			
			return this.biconverter.entityToDto(b1);
	}
}