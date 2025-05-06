package com.dbms.assignment.dto;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        PetSummaryResponse pet,
        VetSummaryResponse vet,
        LocalDateTime appointmentTime,
        String status
) {
}
