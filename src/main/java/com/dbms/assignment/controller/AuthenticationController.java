package com.dbms.assignment.controller;

import com.dbms.assignment.dto.RegisterRequest;
import com.dbms.assignment.dto.UserResponse;
import com.dbms.assignment.model.User;
import com.dbms.assignment.repository.UserRepository;
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

    private final UserRepository userRepository;

    public AuthenticationController(UserRepository userRepository) {
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
    public ResponseEntity<UserResponse> login(@RequestBody LinkedHashMap<String, String> loginPayload) {
        String email = loginPayload.get("email");
        String password = loginPayload.get("password");

        User user = userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        UserResponse res = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
