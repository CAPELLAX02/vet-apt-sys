package com.dbms.assignment.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diagnosis")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    private String notes;

    private LocalDateTime diagnosedAt;

    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions = new HashSet<>();

}
