package com.web.memories.restcontrollers;

import com.web.memories.domain.Memory;
import com.web.memories.repositories.MemoryRepository;
import com.web.memories.security.annotations.CreateMemoryPermission;
import com.web.memories.security.annotations.DeleteMemoryPermission;
import com.web.memories.security.annotations.ReadMemoryPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/memories")
public class MemoryController {
    private final Logger logger = LoggerFactory.getLogger(MemoryController.class);
    private final MemoryRepository memoryRepository;
    public MemoryController(MemoryRepository memoryRepository){
        this.memoryRepository = memoryRepository;
    }
    @ReadMemoryPermission
    @GetMapping(produces = { "application/json" })
    public List<Memory> findAllMemories(){
        logger.info("Get:findAllMemories");
        return memoryRepository.findAll();
    }
    @ReadMemoryPermission
    @GetMapping("/{id}")
    public Optional<Memory> findMemoryById(@PathVariable("id") Long id){
        return memoryRepository.findById(id);
    }
    @DeleteMemoryPermission
    @DeleteMapping("/{id}")
    public Boolean deleteMemory(@PathVariable("id") Long id){
        Optional<Memory> optionalMemory = memoryRepository.findById(id);
        if (optionalMemory.isPresent()){
            memoryRepository.delete(optionalMemory.get());
            return true;
        } else return false;
    }

    @CreateMemoryPermission
    @PostMapping
    public Memory saveMemory(@RequestBody Memory memory){
        return memoryRepository.save(memory);
    }
}
