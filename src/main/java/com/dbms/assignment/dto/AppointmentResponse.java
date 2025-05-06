package com.dbms.assignment.dto;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        PetSummary pet,
        VetSummary vet,
        LocalDateTime appointmentTime,
        String status
) {
}
