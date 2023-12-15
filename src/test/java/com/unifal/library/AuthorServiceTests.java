package com.unifal.library;

import com.unifal.library.entity.Author;
import com.unifal.library.exception.AuthorAlreadyExists;
import com.unifal.library.exception.BookAlreadyExists;
import com.unifal.library.exception.InvalidParameter;
import com.unifal.library.repository.AuthorRepository;
import com.unifal.library.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthorServiceTests {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("#deleteAuthorById > When ID is null > throw an InvalidParameter exception")
    void deleteAuthorByIdWhenIdIsNull() {
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.deleteAuthorById(null)
        );
    }

    @Test
    @DisplayName("#deleteAuthorById > When ID is not null > Does not throw an exception")
    void deleteAuthorByIdWhenIdIsNotNull() {
        int Id = 1;

        authorService.deleteAuthorById(Id);

        Mockito.verify(authorRepository).deleteById(Id);
    }


    @Test
    @DisplayName("#saveNewAuthor > When ID is null > throw an InvalidParameter exception")
    void saveNewAuthorWhenIdIsNull() {
        Author author = new Author();
        author.setFirstName("Matheus");
        author.setLastName("Santos");
        author.setId(null);
        author.setNationality("Brazilian");
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveNewAuthor(author)
        );
    }
    @Test
    @DisplayName("#saveNewAuthor > When first name is null > throw an InvalidParameter exception")
    void saveNewAuthorWhenFirstNameIsNull() {
        Author author = new Author();
        author.setFirstName(null);
        author.setLastName("Santos");
        author.setId(1);
        author.setNationality("Brazilian");
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveNewAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveNewAuthor > When last name is null > throw an InvalidParameter exception")
    void saveNewAuthorWhenLastNameIsNull() {
        Author author = new Author();
        author.setFirstName("Matheus");
        author.setLastName(null);
        author.setId(1);
        author.setNationality("Brazilian");
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveNewAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveNewAuthor > When nationality is null > throw an InvalidParameter exception")
    void saveNewAuthorWhenNationalityIsNull() {
        Author author = new Author();
        author.setFirstName("Matheus");
        author.setLastName("Santos");
        author.setId(1);
        author.setNationality(null);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveNewAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveNewAuthor > When all parameters are null > throw an InvalidParameter exception")
    void saveNewAuthorWhenAllParametersAreNull() {
        Author author = new Author();
        author.setFirstName(null);
        author.setLastName(null);
        author.setId(null);
        author.setNationality(null);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveNewAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveNewAuthor > When parameters are valid but author already exists > throw an AuthorAlreadyExists exception")
    void saveNewAuthorWhenParametersAreNotNullAndBookAlreadyExists() {
        Author author = new Author();
        author.setFirstName("Matheus");
        author.setLastName("Santos");
        author.setId(1);
        author.setNationality("Brazilian");
        Mockito.when(
                authorRepository.existsAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName())
        ).thenReturn(Boolean.TRUE);

        Assertions.assertThrows(
                AuthorAlreadyExists.class,
                () -> authorService.saveNewAuthor(author)
        );

    }
        @Test
        @DisplayName("#saveNewAuthor > When parameters are valid and author does not exist > do not throw exception")
        void saveNewAuthorWhenParametersAreNotNullAndBookDoesNotExist() {
            Author author = new Author();
            author.setFirstName("Matheus");
            author.setLastName("Santos");
            author.setId(1);
            author.setNationality("Brazilian");
            Mockito.when(
                    authorRepository.existsAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName())
            ).thenReturn(Boolean.FALSE);

            Assertions.assertDoesNotThrow(
                    () -> authorService.saveNewAuthor(author)
            );
        }

    @Test
    @DisplayName("#saveEditedAuthor > When ID is null > throw an InvalidParameter exception")
    void saveEditedAuthorWhenIdIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        author.setLastName("Santos");
        author.setId(null);
        author.setNationality("Brazilian");
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveEditedAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveEditedAuthor > When first name is null > throw an InvalidParameter exception")
    void saveEditedAuthorWhenFirstNameIsNull(){
        Author author = new Author();
        author.setFirstName(null);
        author.setLastName("Santos");
        author.setId(1);
        author.setNationality("Brazilian");
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveEditedAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveEditedAuthor > When last name is null > throw an InvalidParameter exception")
    void saveEditedAuthorWhenLastNameIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        author.setLastName(null);
        author.setId(1);
        author.setNationality("Brazilian");
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveEditedAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveEditedAuthor > When nationality is null > throw an InvalidParameter exception")
    void saveEditedAuthorWhenNationalityIsNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        author.setLastName("Santos");
        author.setId(1);
        author.setNationality(null);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveEditedAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveEditedAuthor > When all parameters are null > throw an InvalidParameter exception")
    void saveEditedAuthorWhenAllParametersAreNull(){
        Author author = new Author();
        author.setFirstName(null);
        author.setLastName(null);
        author.setId(null);
        author.setNationality(null);
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.saveEditedAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveEditedAuthor > When parameters are valid but author already exists > throw an AuthorAlreadyExists exception")
    void saveEditedAuthorWhenParametersAreNotNullAndAlreadyExists(){
        Author author = new Author();
        author.setFirstName("Matheus");
        author.setLastName("Santos");
        author.setId(1);
        author.setNationality("Brazilian");
        Mockito.when(
                authorRepository.existsAuthorByFirstNameAndLastName(author.getFirstName(),author.getLastName())
        ).thenReturn(Boolean.TRUE);

        Assertions.assertThrows(
                AuthorAlreadyExists.class,
                () -> authorService.saveEditedAuthor(author)
        );
    }

    @Test
    @DisplayName("#saveEditedAuthor > When parameters are valid > do not throw exception")
    void saveEditedAuthorWhenParametersAreNotNull(){
        Author author = new Author();
        author.setFirstName("Matheus");
        author.setLastName("Santos");
        author.setId(1);
        author.setNationality("Brazilian");
        Mockito.when(
                authorRepository.existsAuthorByFirstNameAndLastName(author.getFirstName(),author.getLastName())
        ).thenReturn(Boolean.FALSE);

        Assertions.assertDoesNotThrow(
                () -> authorService.saveEditedAuthor(author)
        );
    }

    @Test
    @DisplayName("#getAuthorById > When ID is null > throw an InvalidParameter exception")
    void getAuthorByIdWhenIdIsNull(){
        Assertions.assertThrows(
                InvalidParameter.class,
                () -> authorService.getAuthorById(null)
        );
    }

    @Test
    @DisplayName("#getAuthorById > When ID is valid > does not throw a exception")
    void getAuthorByIdWhenIdIsNotNull(){
        authorService.getAuthorById(1);

        Mockito.verify(authorRepository).findById(1);
    }

}
