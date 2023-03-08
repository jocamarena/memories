package com.web.memories.domain.dto.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class RegisterUserDTO {
    private String username;
    private String password;
    private String verifyPassword;
}
