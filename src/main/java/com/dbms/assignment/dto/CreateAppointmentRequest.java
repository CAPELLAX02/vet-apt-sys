package com.dbms.assignment.dto;

import java.time.LocalDateTime;

public record CreateAppointmentRequest(
        Long petId,
        Long vetId,
        LocalDateTime appointmentTime
) {
}
