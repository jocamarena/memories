package com.web.memories.repositories;

import com.web.memories.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    public List<Author> findAllByLastNameIgnoreCaseAndFirstNameIgnoreCase(String lastname, String firstname);
    public List<Author> findAllById(Long id);
}
