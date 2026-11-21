package com.marwadi.bdr.service;

import com.marwadi.bdr.entity.AppUser;
import com.marwadi.bdr.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    // Runs once when the app starts: creates the default admin account if it doesn't exist
    @Override
    public void run(String... args) {
        if (appUserRepository.findByUsername("admin").isEmpty()) {
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPasswordHash(encoder.encode("admin123"));
            admin.setFullName("System Administrator");
            admin.setRole("ADMIN");
            appUserRepository.save(admin);
        }
    }

    // Returns the user if the username and password are correct, otherwise empty
    public Optional<AppUser> login(String username, String rawPassword) {
        Optional<AppUser> user = appUserRepository.findByUsername(username);
        if (user.isPresent() && encoder.matches(rawPassword, user.get().getPasswordHash())) {
            return user;
        }
        return Optional.empty();
    }
}