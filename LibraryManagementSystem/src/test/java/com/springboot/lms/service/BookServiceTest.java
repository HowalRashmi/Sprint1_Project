package com.springboot.lms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.lms.DAO.BookRepository;
import com.springboot.lms.DTO.BookDto;
import com.springboot.lms.converter.BookConverter;
import com.springboot.lms.entities.Book;
import com.springboot.lms.exceptions.BookNotFound;
import com.springboot.lms.exceptions.InsertBookException;
import com.springboot.lms.services.BookService;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookConverter bookConverter;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Test to get all books from the table 
    @Test
    public void testGetAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book());
        bookList.add(new Book());

        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(new BookDto());
        bookDtoList.add(new BookDto());

        doReturn(bookList).when(bookRepository).findAll();
        doReturn(bookDtoList).when(bookConverter).entityToDtoList(bookList);

        List<BookDto> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    
    //Test to get a book record by its id
    @Test
    public void testGetById() throws BookNotFound {
        Book book = new Book();
        book.setBid(1);
        book.setBname("Sample Book");
        book.setBcategory("xyz");
        book.setAuthor("pqr");

        BookDto bookDto = new BookDto();
        bookDto.setBid(book.getBid());
        bookDto.setBname(book.getBname());
        bookDto.setBcategory(book.getBcategory());
        bookDto.setAuthor(book.getAuthor());

        Mockito.when(bookRepository.findById(1)).thenReturn(book);
        doReturn(bookDto).when(bookConverter).entityToDto(book);

        BookDto result = bookService.getById(1);

        assertNotNull(result);
        assertEquals(1, result.getBid());
    }

    
    //Test to add a book into the table
    @Test
    public void testAddBook() throws InsertBookException {
        BookDto bookDto = new BookDto();
        bookDto.setBname("New Book");

        Book book = new Book();
        book.setBname("New Book");

        doReturn(book).when(bookConverter).dtoToEntity(bookDto);
        doReturn(book).when(bookRepository).save(book);
        doReturn(bookDto).when(bookConverter).entityToDto(book);

        BookDto result = bookService.addBook(bookDto);

        assertNotNull(result);
        assertEquals("New Book", result.getBname());
    }

    
    //Test to delete a book 
    @Test
    public void testDeleteBook() throws BookNotFound {
        doNothing().when(bookRepository).deleteById(1);

        bookService.deleteBook(1);
    }

    
  
    //Test to update a book
    @Test
    public void testUpdateBook() throws BookNotFound {
      
        int bookId = 1;
        BookDto bookDto = new BookDto();
        bookDto.setBname("Updated Book");
        bookDto.setBcategory("Updated Category");
        bookDto.setAuthor("Updated Author");

        Book existingBook = new Book();
        existingBook.setBname("Old Book");
        existingBook.setBcategory("Old Category");
        existingBook.setAuthor("Old Author");

        Book updatedBook = new Book();
        updatedBook.setBname("Updated Book");
        updatedBook.setBcategory("Updated Category");
        updatedBook.setAuthor("Updated Author");

        doReturn(existingBook).when(bookRepository).findById(bookId);
        doReturn(updatedBook).when(bookRepository).save(any(Book.class));
        doReturn(bookDto).when(bookConverter).entityToDto(updatedBook);

       
        BookDto result = bookService.updateBook(bookId, bookDto);

        
        assertNotNull(result);
        assertEquals("Updated Book", result.getBname());
        assertEquals("Updated Category", result.getBcategory());
        assertEquals("Updated Author", result.getAuthor());
        verify(bookRepository).findById(bookId);
        verify(bookRepository).save(any(Book.class));
    }
}
