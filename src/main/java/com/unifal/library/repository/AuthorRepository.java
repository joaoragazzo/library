package com.unifal.library.repository;

import com.unifal.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    public Boolean existsAuthorByFirstNameAndLastName(String firstName, String lastName);
}
