package com.dbms.assignment.repository;

import com.dbms.assignment.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    Set<Diagnosis> findAllByAppointment_Pet_Id(Long petId);

}
