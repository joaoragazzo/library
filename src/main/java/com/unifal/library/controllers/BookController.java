package com.unifal.library.controllers;

import com.unifal.library.entity.Author;
import com.unifal.library.entity.Book;
import com.unifal.library.service.AuthorService;
import com.unifal.library.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService service;
    private final AuthorService authorService;

    @GetMapping("")
    public String Book(Model model) {
        return "book";
    }

    @GetMapping("/manager")
    public String booksList(Model model) {

        List<Book> books = service.getAllBooks();

        model.addAttribute("books", books);

        return "/book/manager";
    }

    @GetMapping("/edit/{id}")
    public String bookEdit(Model model, @PathVariable  Integer id) {
        Book book = service.getBookById(id);
        List<Author> authors = authorService.getAllAuthors();

        model.addAttribute("book", book);
        model.addAttribute("authors", authors);

        return "/book/edit";

    }

    @PostMapping("/edit/save")
    public String bookEditSave(Model model, @ModelAttribute("book") Book book) {
        service.saveEditedBook(book);
        return "redirect:/book/manager";
    }

    @PostMapping("/delete/{id}")
    public String bookDelete(Model model, @PathVariable  Integer id) {
        log.info("The id is " + id.toString());
        service.deleteBookById(id);
        return "redirect:/book/manager";
    }

    @GetMapping("/add")
    public String bookAdd(Model model) {
        Book book = new Book();
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("book",book);
        model.addAttribute("authors", authors);

        return "/book/add";
    }

    @PostMapping("/save")
    public String bookSave(@ModelAttribute("book") Book book) {

        service.saveNewBook(book);
        return "redirect:/book/manager";
    }
}
