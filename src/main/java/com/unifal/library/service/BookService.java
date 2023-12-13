package com.unifal.library.service;

import com.unifal.library.entity.Book;
import com.unifal.library.exception.BookAlreadyExists;
import com.unifal.library.exception.BookDontExists;
import com.unifal.library.exception.InvalidParameter;
import com.unifal.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public void deleteBookById(Integer id) throws InvalidParameter {
        if (Objects.isNull(id)) {
            throw new InvalidParameter("Book ID cannot be null");
        }

        repository.deleteById(id);
    }

    public void saveNewBook(Book book) throws InvalidParameter, BookAlreadyExists{
        if (book.getTitle().isBlank() || Objects.isNull(book.getAuthor()) || Objects.isNull(book.getPages()) || Objects.isNull(book.getPublication_year())) {
            throw new InvalidParameter("Book title, author, pages and publication year cannot be null");
        }

        if (!Objects.isNull(book.getId())) {
            throw new InvalidParameter("ID cannot be set.");
        }


        if(repository.existsByTitle(book.getTitle())) {
            throw new BookAlreadyExists("This book already exits");
        }

        repository.save(book);
    }

    public Book getBookById(Integer id) throws InvalidParameter, BookAlreadyExists{
        if (Objects.isNull(id)) {
            throw new InvalidParameter("Book ID cannot be null");
        }

        Optional<Book> book = repository.findById(id);

        return book.orElseThrow(()-> new BookDontExists("This book dont exists"));

    }

    public void saveEditedBook(Book book) throws InvalidParameter, BookAlreadyExists {
        if (book.getTitle().isBlank() || Objects.isNull(book.getAuthor()) || Objects.isNull(book.getPages())
                || Objects.isNull(book.getPublication_year()) || Objects.isNull(book.getId())) {
            throw new InvalidParameter("Book title, author, pages, publication year and ID cannot be null");
        }

        Book oldBook = getBookById(book.getId());
        String oldTitle = oldBook.getTitle();;

        if (oldTitle != book.getTitle() && repository.existsByTitle(book.getTitle())) {
            throw new BookAlreadyExists("This book already exists");
        }

        repository.save(book);
    }
}
