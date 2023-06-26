package com.springboot.lms.converter;

import java.util.List;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.lms.DTO.StudentDto;
import com.springboot.lms.entities.Student;

@Component
public class StudentConverter {


	//converting entity data to dto objects
	public StudentDto entityToDto(Student stud)
	{
		StudentDto sdto = new StudentDto();
		sdto.setSid(stud.getSid());
		sdto.setSname(stud.getSName());
		sdto.setSclass(stud.getSClass());
		sdto.setSbranch(stud.getSBranch());
		sdto.setMobno(stud.getMobno());
		sdto.setSemail(stud.getSEmail());
		return sdto;
	}
	
	//converting entity data to dto object list
	public List<StudentDto> entityToDtoList(List<Student> stud)
	{
		return stud.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
	}
	
	//converting dto objects to entity data
	public Student dtoToEntity(StudentDto sdto)
	{
		Student stud = new Student();
		stud.setSid(sdto.getSid());
		stud.setSName(sdto.getSname());
		stud.setSClass(sdto.getSclass());
		stud.setSBranch(sdto.getSbranch());
		stud.setMobno(sdto.getMobno());
		stud.setSEmail(sdto.getSemail());
		return stud;
	}
	
	//converting dto objects to entity data list
	public List<Student> dtoToEntityList(List<StudentDto> sdto){
		return sdto.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
	}
}
