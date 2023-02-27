package com.web.memories.services;

import com.web.memories.domain.users.User;
import com.web.memories.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public List<User> saveAllUsers(Iterable<User> users){
        return userRepository.saveAll(users);
    }
    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
