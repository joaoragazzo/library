package com.unifal.library.repository;

import com.unifal.library.entity.Author;
import com.unifal.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    public Boolean existsByTitle(String title);
}
