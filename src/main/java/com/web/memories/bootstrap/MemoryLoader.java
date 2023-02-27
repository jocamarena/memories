package com.web.memories.bootstrap;

import com.web.memories.domain.Author;
import com.web.memories.domain.Memory;
import com.web.memories.domain.users.Authority;
import com.web.memories.domain.users.Role;
import com.web.memories.domain.users.User;
import com.web.memories.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class MemoryLoader implements CommandLineRunner {
    private final MemoryService memoryService;
    private final AuthorService authorService;
    private final RoleService roleService;
    private final AuthorityService authorityService;

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public MemoryLoader(MemoryService memoryService,
                        AuthorService authorService,
                        RoleService roleService,
                        UserService userService,
                        AuthorityService authorityService,
                        BCryptPasswordEncoder bCryptPasswordEncoder){
        this.memoryService = memoryService;
        this.authorService = authorService;
        this.roleService = roleService;
        this.userService = userService;
        this.authorityService = authorityService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public void loadUser(){
        Authority createMemoryAuthority = authorityService.saveAuthority(Authority.builder()
                        .permission("create.memory")
                        .build());
        Authority readMemoryAuthority = authorityService.saveAuthority(Authority.builder()
                .permission("read.memory")
                .build());
        Authority updateMemoryAuthority = authorityService.saveAuthority(Authority.builder()
                .permission("update.memory")
                .build());
        Authority deleteMemoryAuthority = authorityService.saveAuthority(Authority.builder()
                .permission("delete.memory")
                .build());

        Authority createAuthorityAuthority = authorityService.saveAuthority(Authority.builder()
                .permission("create.author")
                .build());
        Authority readAuthorityAuthority = authorityService.saveAuthority(Authority.builder()
                .permission("read.author")
                .build());
        Authority updateAuthorityAuthority = authorityService.saveAuthority(Authority.builder()
                .permission("update.author")
                .build());
        Authority deleteAuthorityAuthority = authorityService.saveAuthority(Authority.builder()
                .permission("delete.author")
                .build());

        Role adminRole = roleService.saveRole(Role.builder()
                .roleName("ADMIN")
                .build());

        User adminUser = userService.saveUser(User.builder()
                .username("jcamarena")
                .build());

        adminRole.setAuthorities(new HashSet<>(Set.of(
                createMemoryAuthority, readMemoryAuthority, updateMemoryAuthority, deleteMemoryAuthority,
                createAuthorityAuthority, readAuthorityAuthority,updateAuthorityAuthority, deleteAuthorityAuthority)));

        roleService.saveRole(adminRole);

        adminUser.setPassword(bCryptPasswordEncoder.encode("Clairdel803!"));

        adminUser.setRoles(new HashSet<>(Set.of(adminRole)));

        userService.saveUser(adminUser);


    }
    public void loadMemory(){
        Author author = authorService.saveAuthor(Author.builder()
                .firstName("Jose")
                .lastName("Camarena")
                .build());

        memoryService.saveMemory(Memory.builder()
                .title("On Monday")
                .dateEntry(LocalDate.of(2023, 2, 20))
                .body("The day started at 04:45 am ...")
                .author(authorService.findAllAuthors().get(0))
                .build());

        memoryService.saveMemory(Memory.builder()
                .title("On Tuesday")
                .dateEntry(LocalDate.of(2023, 2, 21))
                .body("The day started at 06:15 am ...")
                .author(authorService.findAllAuthors().get(0))
                .build());

        memoryService.saveMemory(Memory.builder()
                .title("On Wednesday")
                .dateEntry(LocalDate.of(2023, 2, 22))
                .body("The day started at 04:45 am ...")
                .author(authorService.findAllAuthors().get(0))
                .build());


    }
    @Override
    public void run(String... args) throws Exception {
        loadMemory();
        loadUser();
    }
}
