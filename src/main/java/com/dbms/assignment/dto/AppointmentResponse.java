package com.dbms.assignment.dto;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        Long petId,
        Long vetId,
        String vetName,
        String petName,
        String status,
        LocalDateTime appointmentTime
) {
}
