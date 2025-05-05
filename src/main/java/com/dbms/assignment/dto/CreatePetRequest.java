package com.dbms.assignment.dto;

import java.time.LocalDate;

public record CreatePetRequest(
        String name,
        String species,
        String breed,
        String gender,
        LocalDate birthDate,
        Long ownerId
) {
}
