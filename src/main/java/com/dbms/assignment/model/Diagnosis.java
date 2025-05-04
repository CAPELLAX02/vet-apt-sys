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

    public Diagnosis() {}

    public Diagnosis(Long id,
                     Appointment appointment,
                     String description,
                     String notes,
                     LocalDateTime diagnosedAt,
                     Set<Prescription> prescriptions)
    {
        this.id = id;
        this.appointment = appointment;
        this.description = description;
        this.notes = notes;
        this.diagnosedAt = diagnosedAt;
        this.prescriptions = prescriptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getDiagnosedAt() {
        return diagnosedAt;
    }

    public void setDiagnosedAt(LocalDateTime diagnosedAt) {
        this.diagnosedAt = diagnosedAt;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

}
