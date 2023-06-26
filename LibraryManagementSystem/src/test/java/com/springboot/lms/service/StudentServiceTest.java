package com.springboot.lms.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.lms.DAO.StudentRepository;
import com.springboot.lms.DTO.StudentDto;
import com.springboot.lms.entities.Student;
import com.springboot.lms.exceptions.StudentNotFound;
import com.springboot.lms.converter.StudentConverter;
import com.springboot.lms.services.StudentService;

@SpringBootTest
public class StudentServiceTest {

    @Mock
    private StudentRepository studRepository;

    @Mock
    private StudentConverter studConverter;

    @InjectMocks
    private StudentService studService;

    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    //Test to get all students from the table
    @Test
    public void testGetAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1,"ABC","BE","CSE",8090897678L,"abc@gmail.com"));
        studentList.add(new Student(2,"PQR","TE","ENTC",8790876567L,"pqr@gmail.com"));

        List<StudentDto> studentDtoList = new ArrayList<>();
        studentDtoList.add(new StudentDto());
        studentDtoList.add(new StudentDto());

        doReturn(studentList).when(studRepository).findAll();
        doReturn(studentDtoList).when(studConverter).entityToDtoList(studentList);

        List<StudentDto> result = studService.getAllStudents();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    //Test to get a student details by his id
    @Test
    public void testGetById() throws StudentNotFound {
        Student student = new Student();
        student.setSid(1);
        student.setSName("abc");
        student.setSClass("BE");
        student.setSBranch("CSE");
        student.setMobno(8906789578L);
        student.setSEmail("abc@mail.com");

        StudentDto studentDto = new StudentDto();
        studentDto.setSid(student.getSid());
        studentDto.setSname(student.getSName());
        studentDto.setSclass(student.getSClass());
        studentDto.setSbranch(student.getSBranch());
        studentDto.setMobno(student.getMobno());
        studentDto.setSemail(student.getSEmail());

        Mockito.when(studRepository.findById(1)).thenReturn(student);
        doReturn(studentDto).when(studConverter).entityToDto(student);

        StudentDto result = studService.getById(1);

        assertNotNull(result);
        assertEquals(1, result.getSid());
    }

    

    //Test to add a new student record
    @Test
    public void testAddStud() {
        StudentDto studentDto = new StudentDto();
        studentDto.setSid(1);

        Student student = new Student();
        student.setSid(1);

        doReturn(student).when(studConverter).dtoToEntity(studentDto);
        doReturn(student).when(studRepository).save(student);
        doReturn(studentDto).when(studConverter).entityToDto(student);

        StudentDto result = studService.addStud(studentDto);

        assertNotNull(result);
        assertEquals(1, result.getSid());
    }

    //Test to delete a student record from the table
    @Test
    public void testDeleteStudent() {
        doNothing().when(studRepository).deleteById(1);

        studService.deleteStudent(1);
    }


    //Test to update a student's details by his id 
    @Test
    public void testUpdateStud() throws StudentNotFound {
        int studentId = 1;
        StudentDto studentDto = new StudentDto();
        studentDto.setSname("Updated Student");
        studentDto.setSclass("Updated Class");
        studentDto.setSbranch("Updated Branch");
        studentDto.setMobno(4875962032L);
        studentDto.setSemail("Updated Email");

        Student existingStudent = new Student();
        existingStudent.setSName("Old Student");
        existingStudent.setSClass("Old Class");
        existingStudent.setSBranch("Old Branch");
        existingStudent.setMobno(5874596548L);
        existingStudent.setSEmail("Old Email");

        Student updatedStudent = new Student();
        updatedStudent.setSName("Updated Student");
        updatedStudent.setSClass("Updated Class");
        updatedStudent.setSBranch("Updated Branch");
        updatedStudent.setMobno(4875962032L);
        updatedStudent.setSEmail("Updated Email");

        doReturn(existingStudent).when(studRepository).findById(studentId);
        doReturn(updatedStudent).when(studRepository).save(any(Student.class));
        doReturn(studentDto).when(studConverter).entityToDto(updatedStudent);

        StudentDto result = studService.updateStud(studentId, studentDto);

        assertNotNull(result);
        assertEquals("Updated Student", result.getSname());
        assertEquals("Updated Class", result.getSclass());
        assertEquals("Updated Branch", result.getSbranch());
        assertEquals(4875962032L, result.getMobno());
        assertEquals("Updated Email", result.getSemail());
        verify(studRepository).findById(studentId);
        verify(studRepository).save(any(Student.class));
    }

}
