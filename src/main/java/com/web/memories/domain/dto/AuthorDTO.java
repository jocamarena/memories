package com.web.memories.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthorDTO {
    private String firstname;
    private String lastname;
}
