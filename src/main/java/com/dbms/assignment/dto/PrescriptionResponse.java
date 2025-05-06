package com.dbms.assignment.dto;

public record PrescriptionResponse(
        Long id,
        String medicineName,
        String dosage,
        String instructions
) {
}
