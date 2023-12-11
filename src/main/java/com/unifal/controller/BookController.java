package com.unifal.controller;

import com.unifal.entities.Book;
import com.unifal.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    @GetMapping("/manager")
    public String booksList() {
        return null;
    }

    @GetMapping("/edit/{id}")
    public String bookEdit(Integer id) {
        return null;
    }

    @DeleteMapping ("/delete/{id}")
    public String bookDelete(Integer id) {
        return null;
    }

    @PostMapping("/save")
    public String bookSave(Book book) {
        return null;
    }
}
