package com.unifal.library.service;

import com.unifal.library.entity.Author;
import com.unifal.library.exception.AuthorAlreadyExists;
import com.unifal.library.exception.AuthorDontExists;
import com.unifal.library.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public void deleteAuthorById(Integer id) {
        repository.deleteById(id);
    }

    public void saveNewAuthor(Author author) {
        if(repository.existsAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName())) {
            throw new AuthorAlreadyExists("This author already exists");
        }

        repository.save(author);
    }

    public Author getAuthorById(Integer id) {
        Optional<Author> author = repository.findById(id);

        return author.orElseThrow(() -> new AuthorDontExists("This author dont exists"));
    }

    public void saveEditedAuthor(Author author) {
        repository.save(author);
    }
}
