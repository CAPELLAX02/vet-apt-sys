package com.dbms.assignment.model;

import com.dbms.assignment.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_user_email", columnList = "email")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_user_email", columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @OneToMany(mappedBy = "vet")
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "vet")
    private Set<Specialization> specializations = new HashSet<>();

}
