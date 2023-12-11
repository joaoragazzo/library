package com.unifal.library.controllers;

import com.unifal.library.entity.Author;
import com.unifal.library.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;


    @GetMapping
    public String author(Model model) {
        return "author";
    }

    @GetMapping("/manager")
    public String authorList(Model model) {

        List<Author> authors = service.getAllAuthors();

        model.addAttribute("authors", authors);

        return "author/manager";
    }

    @GetMapping("/edit/{id}")
    public String authorEdit(Model model, @PathVariable Integer id) {
        Author author = service.getAuthorById(id);

        model.addAttribute("author", author);

        return "author/edit";
    }

    @PostMapping("/edit/save")
    public String authorEditSave(Model model, @ModelAttribute("author") Author author) {
        service.saveEditedAuthor(author);
        return "redirect:/author/manager";
    }


    @PostMapping("/delete/{id}")
    public String authorDelete(@PathVariable Integer id) {

        service.deleteAuthorById(id);
        return "redirect:/author/manager";
    }

    @GetMapping("/add")
    public String authorAdd(Model model) {
        Author author = new Author();

        model.addAttribute("author", author);

        return "/author/add";
    }

    @PostMapping("/save")
    public String authorSave(@ModelAttribute("author") Author author) {
        service.saveNewAuthor(author);
        return "redirect:/author/manager";

    }
}
