package com.example.marketplace.services;

import com.example.marketplace.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void createUser(User user, MultipartFile file);
}
