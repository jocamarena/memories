package com.web.memories.controllers;

import com.web.memories.domain.Author;
import com.web.memories.domain.Memory;
import com.web.memories.domain.dto.AuthorDTO;
import com.web.memories.domain.dto.MemoryDTO;
import com.web.memories.security.annotations.CreateMemoryPermission;
import com.web.memories.security.annotations.ReadMemoryPermission;
import com.web.memories.services.AuthorService;
import com.web.memories.services.MemoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/memories")
@AllArgsConstructor
public class MemoryDtoController {
    private MemoryService memoryService;
    private AuthorService authorService;

    @PostMapping
    @CreateMemoryPermission
    public ResponseEntity<MemoryDTO> createMemory(@RequestBody MemoryDTO memoryDTO){
        Author author = Author.builder()
                .lastName(memoryDTO.getAuthor().getLastname())
                .firstName(memoryDTO.getAuthor().getFirstname())
                .build();
        authorService.saveAuthor(author);
        Memory memory = Memory.builder()
                .dateEntry(memoryDTO.getDateEntry())
                .title(memoryDTO.getTitle())
                .body(memoryDTO.getBody())
                .author(author)
                .build();
        memoryService.saveMemory(memory);
        ResponseEntity<MemoryDTO> response = new ResponseEntity<>(memoryDTO, HttpStatusCode.valueOf(201));
        return response;
    }
    @ReadMemoryPermission
    @GetMapping(produces = { "application/json" })
    public ResponseEntity<MemoryDTO> findFirstMemoryResponseEntity(){
        List<Memory> memories = memoryService.findAllMemories();
        Memory memory = memories.get(0);
        MemoryDTO memoryDTO = new MemoryDTO();
        AuthorDTO authorDTO = AuthorDTO.builder()
                .firstname("Jose")
                .lastname("Camarena")
                .build();
        memoryDTO.setAuthor(authorDTO);
        BeanUtils.copyProperties(memory, memoryDTO);
        ResponseEntity<MemoryDTO> memoryDTOResponseEntity = new ResponseEntity<>(memoryDTO, HttpStatusCode.valueOf(200));
        return  memoryDTOResponseEntity;
    }
}
