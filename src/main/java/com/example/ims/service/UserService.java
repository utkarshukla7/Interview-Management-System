package com.example.ims.service;

import com.example.ims.entity.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User findByEmail(String email);
    User updateUser(User user);
    String login(User user);
    User findById(Long Id);
    Void deleteUser(Long Id);
}
