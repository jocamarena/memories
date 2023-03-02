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

@WebMvcTest(StringsControllerTest.class)
class StringsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void findGreetingString() throws Exception {
        mockMvc.perform(get("/strings")).andExpect(status().isOk());
    }
}