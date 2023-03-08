package com.web.memories.security;

import com.web.memories.domain.users.Authority;
import com.web.memories.domain.users.User;
import com.web.memories.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username){
        logger.info("loadUserByUsername " + username);
        User user = userService.findUserByUsername(username);
        try {
            return convertToSpringUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public UserDetails convertToSpringUser(User user) throws Exception {
        logger.info("convertToStringUser method");
        try {
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEnabled(),
                    user.getAccountNonExpired(),
                    user.getCredentialsNonExpired(),
                    user.getAccountNonLocked(),
                    user.getAuthorities().stream()
                            .map(Authority::getPermission)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toSet()));
            logger.info("userDetails: " + userDetails);
            return userDetails;
        } catch (Exception e){
            logger.info("exception " + e.getMessage());
            throw new Exception("exception " + e.getMessage());
        }
    }
}
