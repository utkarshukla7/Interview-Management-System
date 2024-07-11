package com.example.ims.service;

import com.example.ims.entity.User;
import com.example.ims.exception.BadCredentialsException;
import com.example.ims.exception.UserNotFoundException;
import com.example.ims.repository.UserRepository;
import com.example.ims.utility.Helper;
import com.example.ims.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User createUser(User user) {
        Helper helper = new Helper();

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            String randomPassword = helper.generateRandomPassword(10);
            user.setPassword(randomPassword);
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);

    }


    @Override
    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + user.getId()));
        existingUser.setName(user.getName());
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        existingUser.setPassword(hashedPassword);
        existingUser.setPhone(user.getPhone());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public String login(User user){
        String email = user.getEmail();
        User userOptional = findByEmail(email);

        if (userOptional == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        User foundUser = userOptional;

        if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return jwtUtil.generateToken(foundUser.getId());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public User findById(Long Id){
        User existingUser = userRepository.findById(Id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + Id));
        return existingUser;
    }

    @Override
    public Void deleteUser(Long Id) {
        User existingUser = userRepository.findById(Id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + Id));
        userRepository.delete(existingUser);
        return null;
    }
}