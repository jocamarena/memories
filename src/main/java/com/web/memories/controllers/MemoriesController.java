package com.web.memories.controllers;


import com.web.memories.domain.Memory;
import com.web.memories.services.MemoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
