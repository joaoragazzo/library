package com.unifal.controller;

import com.unifal.entities.Author;
import com.unifal.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/author")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;


    @GetMapping("/manager")
    public String authorList() {
        return null;
    }

    @GetMapping("/edit/{id}")
    public String authorEdit(Integer id) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String authorDelete(Integer id) {
        return null;
    }

    @PostMapping("/save")
    public String authorSave(Author author) {
        return null;
    }
}
