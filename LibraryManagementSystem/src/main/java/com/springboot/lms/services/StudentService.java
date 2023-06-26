package com.springboot.lms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lms.DAO.StudentRepository;
import com.springboot.lms.DTO.StudentDto;
import com.springboot.lms.entities.Student;
import com.springboot.lms.exceptions.StudentNotFound;
import com.springboot.lms.converter.StudentConverter;

@Service
public class StudentService {
	@Autowired private StudentRepository studrepo;
	@Autowired private StudentConverter sconverter;
	
	//get all students details
	public List<StudentDto> getAllStudents()
	{
		List<Student> stud = (List<Student>)this.studrepo.findAll();
			return sconverter.entityToDtoList(stud);
	}
	
	//get a student record by id
	public StudentDto getById(int sid)
	{
		Student stud= this.studrepo.findById(sid); 
		if(stud!= null)
		{ 
			return this.sconverter.entityToDto(stud);
		}
		else
		{			
			throw new StudentNotFound();			
		}
		
	}
	
	//add a new student record
	public StudentDto addStud(StudentDto s){
		Student stud = this.sconverter.dtoToEntity(s);
		Student result = this.studrepo.save(stud); // save into the data table.
		return this.sconverter.entityToDto(result);
	}	
	
	//delete a student record
	public void deleteStudent(int sid){
		{
			this.studrepo.deleteById(sid);
		}
	}
	
	//update a student record
	public StudentDto updateStud(int sid, StudentDto sdto) throws StudentNotFound
	{
		Student s1 = studrepo.findById(sid);
		s1.setSName(sdto.getSname());
		s1.setSClass(sdto.getSclass());
		s1.setSBranch(sdto.getSbranch());
		s1.setMobno(sdto.getMobno());
		s1.setSEmail(sdto.getSemail());
		studrepo.save(s1);
		return this.sconverter.entityToDto(s1);
	}
}
