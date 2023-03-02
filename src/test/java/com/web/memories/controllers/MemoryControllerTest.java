package com.web.memories.controllers;

import com.web.memories.repositories.MemoryRepository;
import com.web.memories.services.MemoryService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MemoryController.class)
class MemoryControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private MemoryRepository memoryRepository;
    private MemoryService memoryService = new MemoryService(memoryRepository);
    @Test
    void findAllMemories() throws Exception {
        mockMvc.perform(get("/api/v1/memories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}