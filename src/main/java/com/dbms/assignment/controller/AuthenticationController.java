package com.dbms.assignment.controller;

import com.dbms.assignment.dto.RegisterRequest;
import com.dbms.assignment.model.User;
import com.dbms.assignment.repository.UserRepository;
import com.dbms.assignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthenticationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        if (userRepository.findByEmail(req.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the same email already Exists");
        }

        User user = new User();

        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(req.password());
        user.setPhone(req.phone());
        user.setRole(req.role());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LinkedHashMap<String, String> loginPayload) {
        String email = loginPayload.get("email");
        String password = loginPayload.get("password");

        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
