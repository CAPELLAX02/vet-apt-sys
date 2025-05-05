package com.dbms.assignment.service;

import com.dbms.assignment.model.User;
import com.dbms.assignment.model.enums.Role;
import com.dbms.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    public Set<User> getVets() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Role.VET)
                .collect(Collectors.toSet());
    }

    public Set<User> getOwners() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Role.OWNER)
                .collect(Collectors.toSet());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

}
