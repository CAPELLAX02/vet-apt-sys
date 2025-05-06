package com.dbms.assignment.dto;

public record OwnerSummaryResponse(
        Long id,
        String name,
        String email,
        String phone
) {
}
