package com.web.memories.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemoryDTO {
    private String title;
    private String body;
    private LocalDate dateEntry;
    private AuthorDTO author;

}
