package org.example.springinitializr.service;

import java.util.Optional;

import org.example.springinitializr.model.User;
import org.example.springinitializr.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User register(String username, String rawPassword) {
        if (!StringUtils.hasText(username) || !StringUtils.hasText(rawPassword)) {
            throw new IllegalArgumentException("Username and password are required.");
        }
        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("Username already exists");
        }
        String hash = passwordEncoder.encode(rawPassword);
        User user = new User(username, hash);
        return userRepository.save(user);
    }

    public boolean login(String username, String rawPassword) {
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isEmpty()) return false;
        return passwordEncoder.matches(rawPassword, opt.get().getPasswordHash());
    }
}
