package com.web.memories.controllers;

import com.web.memories.domain.Memory;
import com.web.memories.repositories.MemoryRepository;
import com.web.memories.restcontrollers.MemoryController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemoryController.class)
class MemoryControllerTest {
//    private Logger logger = new LoggerFactory
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemoryRepository memoryRepository;
    @Test
    @DisplayName("Memory Repository : Find All Memories")
    @WithMockUser(authorities = {"read.memory"})
    void findAllMemories() throws Exception {
        Memory memory = Memory.builder()
                        .id(1L)
                .title("title")
                .dateEntry(LocalDate.now())
                .body("body").build();
        when(memoryRepository.findAll()).thenReturn(new ArrayList<>(Set.of(memory)));
        mockMvc.perform(get("/api/v1/memories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(1)));
    }
}