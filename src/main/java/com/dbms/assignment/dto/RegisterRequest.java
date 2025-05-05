package com.dbms.assignment.dto;

import com.dbms.assignment.model.enums.Role;

public record RegisterRequest(
        String name,
        String email,
        String password,
        String phone,
        Role role
) {
}
