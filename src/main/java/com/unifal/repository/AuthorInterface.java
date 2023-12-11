package com.unifal.repository;

import com.unifal.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorInterface extends JpaRepository<Author, Integer> {
}
