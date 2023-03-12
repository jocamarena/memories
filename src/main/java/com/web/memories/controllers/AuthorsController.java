package com.web.memories.controllers;

import com.web.memories.domain.Author;
import com.web.memories.domain.Memory;
import com.web.memories.domain.dto.AuthorDTO;
import com.web.memories.services.AuthorService;
import com.web.memories.services.MemoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthorService authorService;
    private final MemoryService memoryService;
    @GetMapping
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "viewauthors.html";
    }

    @GetMapping("/form")
    public String getAddForm(@ModelAttribute AuthorDTO authorDTO, Model model){
        model.addAttribute("author", authorDTO);
        return "addauthor.html";
    }
    @PostMapping("/saved")
    public String saveAuthor(@ModelAttribute AuthorDTO authorDTO, Model model){
        logger.info("AuthorDTO first name:" + authorDTO.getFirstname() + " last name:" + authorDTO.getLastname());
        Author author = Author.builder()
                .firstName(authorDTO.getFirstname())
                .lastName(authorDTO.getLastname())
                .build();
        authorService.saveAuthor(author);
        List<Author> authors = authorService.findAllAuthorsById(author.getId());
        model.addAttribute("authors", authors);
        return "viewauthor";
    }
}
