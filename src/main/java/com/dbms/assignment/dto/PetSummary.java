package com.dbms.assignment.dto;

import java.time.LocalDate;

public record PetSummary(
        Long id,
        String name,
        String species,
        String breed,
        String gender,
        LocalDate birthDate
) {
}
