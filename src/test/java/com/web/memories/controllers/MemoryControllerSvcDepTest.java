package com.web.memories.controllers;

import com.web.memories.domain.Memory;
import com.web.memories.restcontrollers.MemoryControllerSvcDep;
import com.web.memories.services.MemoryService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemoryControllerSvcDep.class)
class MemoryControllerSvcDepTest {
    @Inject
    private MockMvc mockMvc;
    @MockBean
    private MemoryService memoryService;

    @Test
    @DisplayName("FindAllMemories v2 with Service dependency injection")
    @WithMockUser(authorities = {"read.memory"})
    void findAllMemories() throws Exception {
        Memory memory = Memory.builder()
                .id(1L)
                .title("title")
                .dateEntry(LocalDate.now())
                .body("body").build();
        when(memoryService.findAllMemories()).thenReturn(new ArrayList<>(Set.of(memory)));
        mockMvc.perform(get("/api/v2/memories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dateEntry", is("2023-03-05")));
    }
}