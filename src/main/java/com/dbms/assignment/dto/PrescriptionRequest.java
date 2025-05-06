package com.dbms.assignment.dto;

public record PrescriptionRequest(
        Long diagnosisId,
        String medicineName,
        String dosage,
        String instructions
) {
}
