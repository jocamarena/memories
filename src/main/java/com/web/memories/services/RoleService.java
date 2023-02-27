package com.web.memories.services;

import com.web.memories.domain.users.Role;
import com.web.memories.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
    public List<Role> saveAllRoles(Iterable roles){
        return roleRepository.saveAll(roles);
    }
    public Optional<Role> findRoleById(Long id){
        return roleRepository.findById(id);
    }
    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }
}
