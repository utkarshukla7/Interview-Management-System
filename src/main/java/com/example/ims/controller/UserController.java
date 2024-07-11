package com.example.ims.controller;

import com.example.ims.entity.User;
import com.example.ims.exception.UserNotFoundException;
import com.example.ims.exception.BadCredentialsException;
import com.example.ims.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createuser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {

            User getUser = userService.findByEmail(user.getEmail());
            System.out.println(getUser);
            if (getUser != null) {
                return ResponseEntity.badRequest().body("Email already exists");
            }
            if (user.getRole() == null || user.getEmail() == null  || user.getName() == null || user.getPhone() == null) {
                return ResponseEntity.badRequest().body("Invalid request, add all parameters");
            }
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    @PutMapping("/updateuser")
    @PreAuthorize("hasAnyRole('ADMIN','INTERVIEWER','CANDIDATE')")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try{
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        }
        catch(UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in fetching user");

        }
    }


    @GetMapping("/find_user_by_id")
    public ResponseEntity<?> userById(@RequestParam Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in fetching user");
        }
    }


    @GetMapping("/find_user_by_email")
    public ResponseEntity<?> userByEmail(@RequestParam String email) {
        try {
            User user = userService.findByEmail(email);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                throw new UserNotFoundException("user not found: " + email);
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in fetching user");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String token = userService.login(user);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestBody Long Id){
        try{
            userService.deleteUser(Id);
            return ResponseEntity.ok("done");
        }
        catch(UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in deleting user" +e);

        }
    }
}
