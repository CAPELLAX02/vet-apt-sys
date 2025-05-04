package com.dbms.assignment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "specializations")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vet_id", nullable = false)
    private User vet;

    @Column(nullable = false)
    private String title;

}
