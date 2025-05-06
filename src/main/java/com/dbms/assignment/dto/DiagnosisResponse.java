package com.dbms.assignment.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record DiagnosisResponse(
        Long id,
        Long appointmentId,
        String description,
        String notes,
        LocalDateTime diagnosedAt,
        Set<PrescriptionResponse> prescriptions
) {
}
