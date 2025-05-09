package com.dbms.assignment.repository;

import com.dbms.assignment.model.Specialization;
import com.dbms.assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    List<Specialization> findAllByVet(User vet);
    boolean existsByVetAndTitle(User vet, String title);

}
