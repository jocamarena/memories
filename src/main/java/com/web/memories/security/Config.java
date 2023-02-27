package com.web.memories.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Config {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorization) -> authorization
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/memory/update").hasAnyRole("ADMIN")
                                .requestMatchers("/webjars/**", "/css/**", "/images/**", "/h2-console").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf().disable();

        //h2 console config
        http.headers().frameOptions().sameOrigin();;
        return http.build();
    }
}
