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
    @DisplayName("#deleteBookById > When the ID is valid > do not throw a exception")
    void deleteBookByIdWhenIdIsValid(){
        int Id = 1;

        bookservice.deleteBookById(Id);

        Mockito.verify(bookRepository).deleteById(Id);
    }

    @Test
    @DisplayName("#saveNewBook > When title is null > throw an InvalidParameter exception")    void saveNewBookWhenTitleIsNull(){
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
    @DisplayName("#saveNewBook > When author is null > throw an InvalidParameter exception")
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
    @DisplayName("#saveNewBook > When page number is null > throw an InvalidParameter exception")
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
    @DisplayName("#saveNewBook > When publication year is null > throw an InvalidParameter exception")
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
    @DisplayName("#saveNewBook > When all parameters are null > throw an InvalidParameter exception")
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
    @DisplayName("#saveNewBook > When book already exists > throw a BookAlreadyExists exception")
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
    @DisplayName("#saveNewBook > When parameters are valid and book does not exist > do not throw exception")
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

    @Test
    @DisplayName("#saveEditedBook > When title is null > throw an InvalidParameter exception")
    void saveEditedBookWhenTitleIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(1);
        book.setAuthor(author);
        book.setTitle(null);
        book.setPages(100);
        book.setPublication_year(2003);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveEditedBook(book)
        );
    }

    @Test
    @DisplayName("#saveEditedBook > When author is null > throw an InvalidParameter exception")
    void saveEditedBookWhenAuthorIsNull(){
        Book book = new Book();
        book.setId(1);
        book.setAuthor(null);
        book.setTitle("Titulo");
        book.setPages(100);
        book.setPublication_year(2003);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveEditedBook(book)
        );
    }

    @Test
    @DisplayName("#saveEditedBook > When pages is null > throw an InvalidParameter exception")
    void saveEditedBookWhenPagesIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(1);
        book.setAuthor(author);
        book.setTitle("Titulo");
        book.setPages(null);
        book.setPublication_year(2003);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveEditedBook(book)
        );
    }

    @Test
    @DisplayName("#saveEditedBook > When publication year is null > throw an InvalidParameter exception")
    void saveEditedBookWhenPublicationYearIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(1);
        book.setAuthor(author);
        book.setTitle("Titulo");
        book.setPages(100);
        book.setPublication_year(null);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveEditedBook(book)
        );
    }

    @Test
    @DisplayName("#saveEditedBook > When ID is null > throw an InvalidParameter exception")
    void saveEditedBookWhenIdIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(1);
        book.setAuthor(author);
        book.setTitle("Titulo");
        book.setPages(100);
        book.setPublication_year(2003);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.saveEditedBook(book)
        );
    }

    @Test
    @DisplayName("#saveEditedBook > When parameters are valid and book already exists > throw a BookAlreadyExists exception")
    void saveEditedBookWhenParametersAreNotNullAndBookAlreadyExists(){
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(1);
        book.setAuthor(author);
        book.setTitle("Titulo");
        book.setPages(100);
        book.setPublication_year(2003);

        Mockito.when(
                bookRepository.existsByTitle(book.getTitle())
        ).thenReturn(Boolean.TRUE);

        Assertions.assertThrows(
                BookAlreadyExists.class,
                () -> bookservice.saveEditedBook(book)
        );
    }

    @Test
    @DisplayName("#saveEditedBook > When parameters are valid > do not throw exception")
    void saveEditedBookWhenParametersAreNotNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        Book book = new Book();
        book.setId(1);
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

    @Test
    @DisplayName("#getBookById > When ID is null > throw an InvalidParameter exception")
    void getBookIdWhenIdIsNull(){
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> bookservice.getBookById(null)
        );
    }

    @Test
    @DisplayName("#getBookById > When ID is valid > does not throw a exception")
    void getBookWhenIdIsNotNull(){
        bookservice.getBookById(1);

        Mockito.verify(bookRepository).findById(1);
    }
}
