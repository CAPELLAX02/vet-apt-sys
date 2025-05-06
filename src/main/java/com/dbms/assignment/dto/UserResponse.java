package com.dbms.assignment.dto;

import com.dbms.assignment.model.enums.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phone,
        Role role
) {
}
