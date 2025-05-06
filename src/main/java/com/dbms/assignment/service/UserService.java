package com.dbms.assignment.service;

import com.dbms.assignment.dto.UserResponse;
import com.dbms.assignment.model.User;
import com.dbms.assignment.model.enums.Role;
import com.dbms.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toSet());
    }

    public Set<UserResponse> getVets() {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getRole() == Role.VET)
                .map(this::buildUserResponse)
                .collect(Collectors.toSet());
    }

    public Set<UserResponse> getOwners() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Role.OWNER)
                .map(this::buildUserResponse)
                .collect(Collectors.toSet());
    }

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::buildUserResponse)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    private UserResponse buildUserResponse(User u) {
        return new UserResponse(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getPhone(),
                u.getRole()
        );
    }

}
