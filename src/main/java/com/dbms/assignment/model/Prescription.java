package com.dbms.assignment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id", nullable = false)
    private Diagnosis diagnosis;

    private String medicineName;

    private String dosage;

    private String instructions;

}
