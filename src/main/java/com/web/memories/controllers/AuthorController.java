package com.web.memories.controllers;

import com.web.memories.domain.Author;
import com.web.memories.services.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @GetMapping
    public List<Author> findAllAuthors(){
        return authorService.findAllAuthors();
    }
    @GetMapping("/{id}")
    public Optional<Author> findAuthorById(@PathVariable("id") Long id){
        return authorService.findAuthorById(id);
    }
}
