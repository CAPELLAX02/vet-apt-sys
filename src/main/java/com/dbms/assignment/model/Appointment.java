package com.dbms.assignment.model;

import com.dbms.assignment.model.enums.AppointmentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id", nullable = false)
    private User vet;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Diagnosis diagnosis;

}
