package com.springboot.lms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springboot.lms.DTO.StudentDto;
import com.springboot.lms.controllers.StudentController;
import com.springboot.lms.services.StudentService;


public class StudentControllerTest {

	@InjectMocks
    private StudentController studController;

    @Mock
    private StudentService studService;

    @BeforeEach
    public void setup() {
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStudents() throws Exception {
    	StudentDto s1 = new StudentDto(1, "ABC", "BE", "CSE", 7596458210L, "abc@gmail.com");
    	StudentDto s2 = new StudentDto(2, "PQR", "TE", "EnTC", 4352679809L, "pqr@gmail.com");
        List<StudentDto> studList = Arrays.asList(s1, s2);

        when(studService.getAllStudents()).thenReturn(studList);
        ResponseEntity<List<StudentDto>> response =studController.getStudents();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(studList, response.getBody());
        verify(studService, times(1)).getAllStudents();
       
    }
    
    @Test
    public void testGetBookById() throws Exception {
        int sId = 1;
        StudentDto studDto = new StudentDto(1, "ABC", "BE", "CSE", 7596458210L, "abc@gmail.com");

        when(studService.getById(sId)).thenReturn(studDto);
        ResponseEntity<StudentDto> response = studController.getStud(sId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(studDto, response.getBody());
        verify(studService, times(1)).getById(sId);
    }

    @Test
    public void testAddBook() throws Exception {
    	StudentDto studDto = new StudentDto(1, "ABC", "BE", "CSE", 7596458210L, "abc@gmail.com");
        when(studService.addStud(any(StudentDto.class))).thenReturn(studDto);

        // Act
        ResponseEntity<String> response = studController.addStud(studDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(studService, times(1)).addStud(studDto);
    }

    @Test
    public void testDeleteBook() {
        // Arrange
        int sId = 1;
        doNothing().when(studService).deleteStudent(sId);;

        // Act
        ResponseEntity<String> response = studController.deleteStud(sId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(studService, times(1)).deleteStudent(sId);;
    }

    @Test
    void testUpdateBooks() {
        // Arrange
        int sId = 1;
        StudentDto studDto = new StudentDto(1, "ABC", "BE", "CSE", 7596458210L, "abc@gmail.com");

        when(studService.updateStud(1, studDto)).thenReturn(studDto);

        // Act
        ResponseEntity<String> response = studController.updateStud(studDto, 1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(studService, times(1)).updateStud(sId, studDto);
    }
}
