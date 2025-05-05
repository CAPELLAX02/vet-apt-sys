package com.dbms.assignment.controller;

import com.dbms.assignment.model.User;
import com.dbms.assignment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/vets")
    public ResponseEntity<Set<User>> getVets() {
        return ResponseEntity.ok(userService.getVets());
    }

    @GetMapping("/owners")
    public ResponseEntity<Set<User>> getOwners() {
        return ResponseEntity.ok(userService.getOwners());
    }

}
