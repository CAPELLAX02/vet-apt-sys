package com.dbms.assignment.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "prescriptions",
        indexes = {
                @Index(
                        name = "idx_prescription_diagnosis",
                        columnList = "diagnosis_id"
                )
        }
)
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_id", nullable = false)
    private Diagnosis diagnosis;

    private String medicineName;

    private String dosage;

    private String instructions;

    public Prescription() {}

    public Prescription(Long id,
                        Diagnosis diagnosis,
                        String medicineName,
                        String dosage,
                        String instructions) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

}
