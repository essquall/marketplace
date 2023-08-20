package com.example.marketplace.services;

import com.example.marketplace.model.Role;
import com.example.marketplace.model.User;
import com.example.marketplace.repositories.UserRepository;
import com.example.marketplace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(User user) {
        user.setActive(true);
        user.getRoles().add(Role.USER);
        user.setCreationTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}