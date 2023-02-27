package com.web.memories.services;

import com.web.memories.domain.users.Authority;
import com.web.memories.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    public AuthorityService(AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
    }
    public Authority saveAuthority(Authority authority){
        return authorityRepository.save(authority);
    }
    public List<Authority> saveAllAuthorities(Iterable<Authority> authorities){
        return authorityRepository.saveAll(authorities);
    }
    public Optional<Authority> findAuthorityById(Long id){
        return authorityRepository.findById(id);
    }
    public List<Authority> findAllAuthorities(){
        return authorityRepository.findAll();
    }
}
