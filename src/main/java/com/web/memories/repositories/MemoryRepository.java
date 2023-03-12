package com.web.memories.repositories;

import com.web.memories.domain.Author;
import com.web.memories.domain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
    public List<Memory> findMemoriesByAuthor(Author author);
}
