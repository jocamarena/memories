package com.web.memories.controllers;

import com.web.memories.domain.Memory;
import com.web.memories.security.annotations.ReadMemoryPermission;
import com.web.memories.services.MemoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/memories")
@AllArgsConstructor
public class MemoryControllerSvcDep {
    private MemoryService memoryService;

    @ReadMemoryPermission
    @GetMapping(produces = { "application/json" })
    public List<Memory> findAllMemories(){
        return memoryService.findAllMemories();
    }
}
