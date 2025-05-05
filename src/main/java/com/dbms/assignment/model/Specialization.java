package com.dbms.assignment.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "specializations",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_vet_specialization",
                        columnNames = {
                                "vet_id",
                                "title"
                        }
                )
        },
        indexes = {
                @Index(name = "idx_specialization_vet", columnList = "vet_id")
        }
)

public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id", nullable = false)
    private User vet;

    @Column(nullable = false)
    private String title;

    public Specialization() {}

    public Specialization(Long id,
                          User vet,
                          String title)
    {
        this.id = id;
        this.vet = vet;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getVet() {
        return vet;
    }

    public void setVet(User vet) {
        this.vet = vet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
