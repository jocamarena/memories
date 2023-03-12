package com.web.memories.restcontrollers;

import com.web.memories.domain.Memory;
import com.web.memories.domain.dto.MemoryDTO;
import com.web.memories.security.annotations.ReadMemoryPermission;
import com.web.memories.services.MemoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @ReadMemoryPermission
    @GetMapping(produces = { "application/json" }, value = "/dto")
    public MemoryDTO findFirstMemoryDto(){
        List<Memory> memories = memoryService.findAllMemories();
        Memory memory = memories.get(0);
        MemoryDTO memoryDTO = new MemoryDTO();
        BeanUtils.copyProperties(memory, memoryDTO);
        return  memoryDTO;
    }
/*    @ReadMemoryPermission
    @GetMapping(produces = { "application/json" }, value = "/responsedto")
    public ResponseEntity<MemoryDTO> findFirstMemoryResponseEntity(){
        List<Memory> memories = memoryService.findAllMemories();
        Memory memory = memories.get(0);
        MemoryDTO memoryDTO = new MemoryDTO();
        BeanUtils.copyProperties(memory, memoryDTO);
        ResponseEntity<MemoryDTO> memoryDTOResponseEntity = new ResponseEntity<>(memoryDTO, HttpStatusCode.valueOf(201));
        return  memoryDTOResponseEntity;
    }*/
}
