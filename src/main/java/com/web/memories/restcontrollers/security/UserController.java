package com.web.memories.restcontrollers.security;

import com.web.memories.domain.dto.security.RegisterUserDTO;
import com.web.memories.domain.dto.security.UserDTO;
import com.web.memories.domain.users.User;
import com.web.memories.security.annotations.CreateMemoryPermission;
import com.web.memories.services.RoleService;
import com.web.memories.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    @CreateMemoryPermission
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(registerUserDTO, userDTO);
        if (userService.findUserByUsername(registerUserDTO.getUsername()) == null){
           User user = User.builder()
                   .username(registerUserDTO.getUsername())
                   .password(registerUserDTO.getPassword())
                   .build();
           user.setRoles(Set.of(roleService.findRoleByRoleName("READ_WRITE")));
           userService.saveUser(user);
            userDTO.setMessage("Saved");
           return new ResponseEntity<>(userDTO, HttpStatusCode.valueOf(201));
       } else {
            userDTO.setMessage("Username previously registered");
            return new ResponseEntity<>(userDTO, HttpStatusCode.valueOf(200));
        }
    }
}
