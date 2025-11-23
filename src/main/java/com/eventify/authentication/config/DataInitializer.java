package com.eventify.authentication.config;

import com.eventify.authentication.entity.User;
import com.eventify.authentication.entity.enums.Role;
import com.eventify.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initAdmin() {
        return args -> {
            if (!userRepository.existsByEmail("admin@eventify.com")) {

                User admin = User.builder()
                        .name("Super Admin")
                        .email("admin@eventify.com")
                        .password(passwordEncoder.encode("admin123")) // Default password
                        .role(Role.ROLE_ADMIN)
                        .build();

                userRepository.save(admin);
                System.out.println("ADMIN USER CREATED: admin@eventify.com / admin123");
            }
        };
    }
}