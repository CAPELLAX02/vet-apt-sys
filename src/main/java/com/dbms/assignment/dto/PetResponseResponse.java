package com.dbms.assignment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PetResponseResponse(
        Long id,
        String name,
        String species,
        String breed,
        String gender,
        LocalDate birthDate,
        LocalDateTime registeredAt,
        OwnerSummaryResponse owner
) {
}
