package com.unifal.library;

import com.unifal.library.entity.Author;
import com.unifal.library.entity.Book;
import com.unifal.library.exception.BookAlreadyExists;
import com.unifal.library.exception.InvalidParameter;
import com.unifal.library.repository.BookRepository;
import com.unifal.library.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceTests {

    @InjectMocks
    private BookService bookservice;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("#deleteBookById > When the ID is invalid > throw an exception")
    void deleteBookByIdWhenIdIsInvalid() {
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.deleteBookById(null)
        );
    }

    @Test
    @DisplayName("")
    void deleteBookByIdWhenIdIsValid(){
    }

    @Test
    @DisplayName("")
    void saveNewBookWhenTitleIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(null);
        book.setAuthor(author);
        book.setTitle(null);
        book.setPages(100);
        book.setPublication_year(2003);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveNewBook(book)
        );
    }

    @Test
    @DisplayName("")
    void saveNewBookWhenAuthorIsNull(){
        Book book = new Book();
        book.setId(null);
        book.setAuthor(null);
        book.setTitle("Titulo");
        book.setPages(100);
        book.setPublication_year(2003);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveNewBook(book)
        );
    }

    @Test
    @DisplayName("")
    void saveNewBookWhenPageNumberIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(null);
        book.setAuthor(author);
        book.setTitle("Titulo");
        book.setPages(null);
        book.setPublication_year(2003);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveNewBook(book)
        );
    }

    @Test
    @DisplayName("")
    void saveNewBookWhenPublicationYearIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(null);
        book.setAuthor(author);
        book.setTitle("Titulo");
        book.setPages(100);
        book.setPublication_year(null);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveNewBook(book)
        );
    }

    @Test
    @DisplayName("")
    void saveNewBookWhenAllParametersAreNull() {
        Book book = new Book();
        book.setId(null);
        book.setAuthor(null);
        book.setTitle(null);
        book.setPages(null);
        book.setPublication_year(null);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveNewBook(book)
        );

    }
    @Test
    @DisplayName("")
    void saveNewBookWhenBookAlreadyExists() {
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(null);
        book.setAuthor(author);
        book.setTitle("Titulo");
        book.setPages(100);
        book.setPublication_year(2003);

        Mockito.when(
                bookRepository.existsByTitle(book.getTitle())
        ).thenReturn(Boolean.TRUE);

        Assertions.assertThrows(
                BookAlreadyExists.class,
                () -> bookservice.saveNewBook(book)
        );
    }

    @Test
    @DisplayName("")
    void saveNewBookWhenParametersAreNotNullAndBookDoesNotExists() {
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(null);
        book.setAuthor(author);
        book.setTitle("Titulo");
        book.setPages(100);
        book.setPublication_year(2003);

        Mockito.when(
                bookRepository.existsByTitle(book.getTitle())
        ).thenReturn(Boolean.FALSE);

        Assertions.assertDoesNotThrow(
                () -> bookservice.saveNewBook(book)
        );
    }
}
