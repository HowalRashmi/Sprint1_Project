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


import com.springboot.lms.DTO.BookDto;
import com.springboot.lms.controllers.BookController;
import com.springboot.lms.services.BookService;


public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    

    @BeforeEach
    public void setup() {
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBooks() throws Exception {
    	BookDto b1 = new BookDto(1, "Book1", "Category1", "Author1");
    	BookDto b2 = new BookDto(2, "Book2", "Category2", "Author2");
        List<BookDto> bookList = Arrays.asList(b1, b2);

        when(bookService.getAllBooks()).thenReturn(bookList);
        ResponseEntity<List<BookDto>> response = bookController.getBooks();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(bookList, response.getBody());
        verify(bookService, times(1)).getAllBooks();

       
    }

    @Test
    public void testGetBookById() throws Exception {
        int bookId = 1;
        BookDto bookDto = new BookDto(1, "Book1", "Category", "Author1");

        when(bookService.getById(bookId)).thenReturn(bookDto);
        ResponseEntity<BookDto> response = bookController.getBook(bookId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(bookDto, response.getBody());
        verify(bookService, times(1)).getById(bookId);
    }

    @Test
    public void testAddBook() throws Exception {
        BookDto bookDto = new BookDto(1, "Book1","BookCategory1", "Author1");
        when(bookService.addBook(any(BookDto.class))).thenReturn(bookDto);

        // Act
        ResponseEntity<String> response = bookController.addBook(bookDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("insert", response.getBody());
        verify(bookService, times(1)).addBook(bookDto);
    }

    @Test
    public void testDeleteBook() {
        // Arrange
        int bookId = 1;
        doNothing().when(bookService).deleteBook(bookId);

        // Act
        ResponseEntity<String> response = bookController.deleteBook(bookId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(bookService, times(1)).deleteBook(bookId);
    }

    @Test
    void testUpdateBooks() {
        // Arrange
        int bookId = 1;
        BookDto book = new BookDto(1, "Book 1", "Category", "Author");

        when(bookService.updateBook(1, book)).thenReturn(book);

        // Act
        ResponseEntity<String> response = bookController.updateBook(book, 1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("updated", response.getBody());
        verify(bookService, times(1)).updateBook(bookId, book);
    }
}



