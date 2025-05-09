package com.dbms.assignment.dto;

public record SpecializationResponse(
        Long id,
        Long vetId,
        String vetName,
        String title
) {
}
