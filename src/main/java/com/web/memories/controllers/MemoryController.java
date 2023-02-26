package com.web.memories.controllers;

import com.web.memories.domain.Memory;
import com.web.memories.services.MemoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/memories")
public class MemoryController {
    private final MemoryService memoryService;
    public MemoryController(MemoryService memoryService){
        this.memoryService = memoryService;
    }
    @GetMapping
    public List<Memory> findAllMemories(){
        return memoryService.findAllMemories();
    }
    @GetMapping("/{id}")
    public Optional<Memory> findMemoryById(@PathVariable("id") Long id){
        return memoryService.findMemoryById(id);
    }
    @DeleteMapping("/{id}")
    public Boolean deleteMemory(@PathVariable("id") Long id){
        Optional<Memory> optionalMemory = memoryService.findMemoryById(id);
        if (optionalMemory.isPresent()){
            memoryService.deleteMemory(optionalMemory.get());
            return true;
        } else return false;
    }
    @PostMapping
    public Memory saveMemory(@RequestBody Memory memory){
        return memoryService.saveMemory(memory);
    }
}
