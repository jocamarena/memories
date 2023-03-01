package com.web.memories.controllers;

import com.web.memories.domain.Memory;
import com.web.memories.security.annotations.CreateMemoryPermission;
import com.web.memories.security.annotations.DeleteMemoryPermission;
import com.web.memories.security.annotations.ReadMemoryPermission;
import com.web.memories.services.MemoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/memories")
public class MemoryController {
    private final Logger logger = LoggerFactory.getLogger(MemoryController.class);
    private final MemoryService memoryService;
    public MemoryController(MemoryService memoryService){
        this.memoryService = memoryService;
    }
    @ReadMemoryPermission
    @GetMapping(produces = { "application/json" })
    public List<Memory> findAllMemories(){
        logger.info("Get:findAllMemories");
        return memoryService.findAllMemories();
    }
    @ReadMemoryPermission
    @GetMapping("/{id}")
    public Optional<Memory> findMemoryById(@PathVariable("id") Long id){
        return memoryService.findMemoryById(id);
    }
    @DeleteMemoryPermission
    @DeleteMapping("/{id}")
    public Boolean deleteMemory(@PathVariable("id") Long id){
        Optional<Memory> optionalMemory = memoryService.findMemoryById(id);
        if (optionalMemory.isPresent()){
            memoryService.deleteMemory(optionalMemory.get());
            return true;
        } else return false;
    }

    @CreateMemoryPermission
    @PostMapping
    public Memory saveMemory(@RequestBody Memory memory){
        return memoryService.saveMemory(memory);
    }
}
