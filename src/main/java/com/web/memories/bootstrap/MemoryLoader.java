package com.web.memories.bootstrap;

import com.web.memories.domain.Author;
import com.web.memories.domain.Memory;
import com.web.memories.domain.users.Authority;
import com.web.memories.domain.users.Role;
import com.web.memories.domain.users.RoleType;
import com.web.memories.domain.users.User;
import com.web.memories.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(MemoryLoader.class);
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
                .roleType(RoleType.ADMIN)
                .build());

        Role authorRole = roleService.saveRole(Role.builder()
                .roleName("AUTHOR")
                .build());

        Role readWriteMemoryRole = roleService.saveRole(Role.builder()
                .roleName("READ_WRITE")
                .build());

        User adminUser = userService.saveUser(User.builder()
                .username("jcamarena")
                .build());

        User authorUser = userService.saveUser(User.builder()
                .username("kcamarena")
                .build());

        User memoryUser = userService.saveUser(User.builder()
                .username("jcam")
                .build());

        adminRole.setAuthorities(new HashSet<>(Set.of(
                createMemoryAuthority, readMemoryAuthority, updateMemoryAuthority, deleteMemoryAuthority,
                createAuthorityAuthority, readAuthorityAuthority,updateAuthorityAuthority, deleteAuthorityAuthority)));

        authorRole.setAuthorities(new HashSet<>(Set.of(
                createMemoryAuthority, readMemoryAuthority, updateMemoryAuthority, deleteMemoryAuthority)));

        readWriteMemoryRole.setAuthorities(new HashSet<>(Set.of(
                createMemoryAuthority, readMemoryAuthority, createAuthorityAuthority, readAuthorityAuthority)));

        roleService.saveAllRoles(Set.of(adminRole, authorRole, readWriteMemoryRole));

        adminUser.setPassword(bCryptPasswordEncoder.encode("Clairdel803!"));

        adminUser.setRoles(new HashSet<>(Set.of(adminRole)));

        authorUser.setPassword(bCryptPasswordEncoder.encode("12037@166th"));

        authorUser.setRoles(new HashSet<>(Set.of(authorRole)));

        memoryUser.setPassword(bCryptPasswordEncoder.encode("Clairdel803!"));

        memoryUser.setRoles(new HashSet<>(Set.of(readWriteMemoryRole)));

        userService.saveAllUsers(Set.of(adminUser, authorUser, memoryUser));


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
