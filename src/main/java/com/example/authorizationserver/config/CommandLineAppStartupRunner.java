package com.example.authorizationserver.config;

import com.example.authorizationserver.entities.User;
import com.example.authorizationserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String...args) {
        User admin = new User();
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setEmail("okemedjbabs@gmail.com");
        admin.setPassword(passwordEncoder.encode("password"));

        userRepository.save(admin);
    }
}