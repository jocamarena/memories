package com.web.memories.bootstrap;

import com.web.memories.domain.Author;
import com.web.memories.domain.Memory;
import com.web.memories.services.AuthorService;
import com.web.memories.services.MemoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MemoryLoader implements CommandLineRunner {
    private final MemoryService memoryService;
    private final AuthorService authorService;
    public MemoryLoader(MemoryService memoryService, AuthorService authorService){
        this.memoryService = memoryService;
        this.authorService = authorService;
    }
    public void loadMemory(){
        Author author = authorService.saveAuthor(Author.builder()
                .firstName("Jose")
                .lastName("Camarena")
                .build());

        memoryService.saveMemory(Memory.builder()
                .title("On Monday")
                .dateEntry(LocalDate.of(2023, 2, 20))
                .body("The day started at 04:45 am ...")
                .author(authorService.findAllAuthors().get(0))
                .build());

        memoryService.saveMemory(Memory.builder()
                .title("On Tuesday")
                .dateEntry(LocalDate.of(2023, 2, 21))
                .body("The day started at 06:15 am ...")
                .author(authorService.findAllAuthors().get(0))
                .build());

        memoryService.saveMemory(Memory.builder()
                .title("On Wednesday")
                .dateEntry(LocalDate.of(2023, 2, 22))
                .body("The day started at 04:45 am ...")
                .author(authorService.findAllAuthors().get(0))
                .build());


    }
    @Override
    public void run(String... args) throws Exception {
        loadMemory();
    }
}
