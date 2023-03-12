package com.web.memories.controllers;


import com.web.memories.domain.Memory;
import com.web.memories.domain.dto.AuthorDTO;
import com.web.memories.domain.dto.MemoryDTO;
import com.web.memories.services.MemoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/memories")
@RequiredArgsConstructor
public class MemoriesController {
    private final MemoryService memoryService;
    @GetMapping
    public String findAllMemories(Model model){
        model.addAttribute("memories", memoryService.findAllMemories());
        return "memories.html";
    }

    @GetMapping("/form")
    public String addMemoryForm(@ModelAttribute MemoryDTO memoryDTO, Model model){
        model.addAttribute("memorydto", memoryDTO);
        return "addmemory.html";
    }
    @PostMapping("/add")
    public String saveNewMemory(@ModelAttribute MemoryDTO memoryDTO, Model model){
        model.addAttribute("memorydto", memoryDTO);
        return "memories.html";
    }
}
