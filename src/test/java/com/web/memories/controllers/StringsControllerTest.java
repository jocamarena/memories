package com.web.memories.controllers;

import com.web.memories.repositories.MemoryRepository;
import com.web.memories.services.MemoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StringsController.class)
class StringsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void getLocalDate(){
        LocalDate localDate = LocalDate.now();
    }
    @Test
    void findGreetingString() throws Exception {
        mockMvc.perform(get("/strings").with(user("user"))).andExpect(status().isOk());
    }
}