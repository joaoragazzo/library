package com.unifal.library.service;

import com.unifal.library.entity.Book;
import com.unifal.library.exception.BookAlreadyExists;
import com.unifal.library.exception.BookDontExists;
import com.unifal.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public void deleteBookById(Integer id) {
        repository.deleteById(id);
    }

    public void saveNewBook(Book book) {
        if(repository.existsByTitle(book.getTitle())) {
            throw new BookAlreadyExists("This book already exits");
        }

        repository.save(book);
    }

    public Book getBookById(Integer id) {
        Optional<Book> book = repository.findById(id);

        return book.orElseThrow(()-> new BookDontExists("This book dont exists"));

    }

    public void saveEditedBook(Book book) {
        repository.save(book);
    }
}
