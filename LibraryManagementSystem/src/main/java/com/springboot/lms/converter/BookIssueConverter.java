package com.springboot.lms.converter;

import java.util.List;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.lms.DTO.BookIssueDto;
import com.springboot.lms.entities.BookIssue;

@Component
public class BookIssueConverter {


	//converting entity data to dto objects
	public BookIssueDto entityToDto(BookIssue bi)
	{
		BookIssueDto bidto= new BookIssueDto();
		bidto.setBid(bi.getId());
		bidto.setBissuedate(bi.getIssueDate());
		bidto.setBreturndate(bi.getReturnDate());
		bidto.setBreturnstatus(bi.getReturnStatus());
		bidto.setBook(bi.getBook());
		bidto.setStud(bi.getStud());
		return bidto;
	}
	
	//converting entity data to dto object list
	public List<BookIssueDto> entityToDtoList(List<BookIssue> bi)
	{
		return bi.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
	}
	
	//converting dto objects to entity data
	public BookIssue dtoToEntity(BookIssueDto bidto)
	{
		BookIssue bi = new BookIssue();
		bi.setId(bidto.getBid());
		bi.setIssueDate(bidto.getBissuedate());
		bi.setReturnDate(bidto.getBreturndate());
		bi.setReturnStatus(bidto.getBreturnstatus());
		bi.setBook(bidto.getBook());
		bi.setStud(bidto.getStud());
		return bi;
	}
	
	//converting dto objects to entity data list
	public List<BookIssue> dtoToEntityList(List<BookIssueDto> bidto)
	{
		return bidto.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
	}
}
