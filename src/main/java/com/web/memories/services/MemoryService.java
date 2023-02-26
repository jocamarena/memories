package com.web.memories.services;

import com.web.memories.domain.Memory;
import com.web.memories.repositories.MemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemoryService {
    private final Logger logger = LoggerFactory.getLogger(MemoryService.class);
    private final MemoryRepository memoryRepository;
    public MemoryService(MemoryRepository memoryRepository){
        this.memoryRepository = memoryRepository;
    }
    public Memory saveMemory(Memory memory){
        return memoryRepository.save(memory);
    }
    public void saveAllMemories(Iterable<Memory> memories){
        memoryRepository.saveAll(memories);
    }
    public void deleteMemory(Memory memory){
        memoryRepository.delete(memory);
    }
    public void deleteAllMemories(Iterable<Memory> memories){
        memoryRepository.deleteAll(memories);
    }
    public Optional<Memory> findMemoryById(Long id){
        return memoryRepository.findById(id);
    }
    public List<Memory> findAllMemories(){
        return memoryRepository.findAll();
    }
}
