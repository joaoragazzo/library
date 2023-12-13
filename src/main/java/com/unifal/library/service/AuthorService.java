package com.unifal.library.service;

import com.unifal.library.entity.Author;
import com.unifal.library.exception.AuthorAlreadyExists;
import com.unifal.library.exception.AuthorDontExists;
import com.unifal.library.exception.InvalidParameter;
import com.unifal.library.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public void deleteAuthorById(Integer id) throws InvalidParameter{
        if (Objects.isNull(id)) {
            throw new InvalidParameter("Author ID cannot be null.");
        }

        repository.deleteById(id);
    }

    public void saveNewAuthor(Author author) throws InvalidParameter, AuthorAlreadyExists{
        if (author.getFirstName().isBlank() || author.getLastName().isBlank() || author.getNationality().isBlank()) {
            throw new InvalidParameter("Author firstname, lastname and nationality must not be null");
        }

        if (!Objects.isNull(author.getId())) {
            throw new InvalidParameter("Author ID cannot be null");
        }

        if(repository.existsAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName())) {
            throw new AuthorAlreadyExists("This author already exists");
        }

        repository.save(author);
    }

    public Author getAuthorById(Integer id) throws InvalidParameter, AuthorAlreadyExists{
        if (Objects.isNull(id)) {
            throw new InvalidParameter("Author ID cannot be null.");
        }

        Optional<Author> author = repository.findById(id);

        return author.orElseThrow(() -> new AuthorDontExists("This author dont exists"));
    }

    public void saveEditedAuthor(Author author) throws InvalidParameter, AuthorAlreadyExists{
        if (Objects.isNull(author.getId()) || author.getFirstName().isBlank() || author.getLastName().isBlank() || author.getNationality().isBlank()) {
            throw new InvalidParameter("Author ID, firstname, lastname and nationality must not be null");
        }

        Author oldAuthor = getAuthorById(author.getId());
        String oldFirstName = oldAuthor.getFirstName();
        String oldLastName = oldAuthor.getLastName();


        if((repository.existsAuthorByFirstName(author.getFirstName()) && oldFirstName != author.getFirstName()) &&
                (repository.existsAuthorByLastName(author.getLastName()) && author.getLastName() != oldLastName)) {
            throw new AuthorAlreadyExists("This author already exists.");
        }


        repository.save(author);
    }
}
