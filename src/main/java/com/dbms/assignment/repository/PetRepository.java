package com.dbms.assignment.repository;

import com.dbms.assignment.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
