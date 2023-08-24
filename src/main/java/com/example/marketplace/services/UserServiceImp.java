package com.example.marketplace.services;

import com.example.marketplace.model.Picture;
import com.example.marketplace.model.Role;
import com.example.marketplace.model.User;
import com.example.marketplace.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final ProductService productService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(User user, MultipartFile avatarFile) {
        Picture avatar = productService.parseToPicture(avatarFile);
        user.setActive(true);
        user.setAvatar(avatar);
        user.getRoles().add(Role.USER);
        user.setCreationTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}