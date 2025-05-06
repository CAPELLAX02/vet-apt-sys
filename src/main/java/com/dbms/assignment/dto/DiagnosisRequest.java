package com.dbms.assignment.dto;

public record DiagnosisRequest(
        Long appointmentId,
        String description,
        String notes
) {
}
