package com.web.memories.services;

import com.web.memories.domain.Author;
import com.web.memories.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }
    public List<Author> saveAllAuthors(Iterable<Author> authors){
        return authorRepository.saveAll(authors);
    }
    public List<Author> findAllAuthors(){
        return authorRepository.findAll();
    }
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }
}
