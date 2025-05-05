package com.dbms.assignment.repository;

import com.dbms.assignment.model.Appointment;
import com.dbms.assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByVetAndAppointmentTime(User vet, LocalDateTime time);

}
