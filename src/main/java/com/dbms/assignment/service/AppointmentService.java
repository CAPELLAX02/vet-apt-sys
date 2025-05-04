package com.dbms.assignment.service;

import com.dbms.assignment.model.Appointment;
import com.dbms.assignment.model.Pet;
import com.dbms.assignment.model.User;
import com.dbms.assignment.model.enums.AppointmentStatus;
import com.dbms.assignment.repository.AppointmentRepository;
import com.dbms.assignment.repository.PetRepository;
import com.dbms.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PetRepository petRepository,
                              UserRepository userRepository)
    {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    public Appointment createAppointment(Long petId, Long vetId, LocalDateTime time) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet with id " + petId + " not found"));
        User vet = userRepository.findById(vetId).orElseThrow(() -> new RuntimeException("User with id " + vetId + " not found"));
        Appointment appointment = new Appointment();
        appointment.setPet(pet);
        appointment.setVet(vet);
        appointment.setAppointmentTime(time);
        appointment.setStatus(AppointmentStatus.PENDING);
        return appointmentRepository.save(appointment);
    }

    public Set<Appointment> getAppointmentsForVet(Long vetId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.getVet().getId().equals(vetId))
                .collect(Collectors.toSet());
    }

    public void cancelAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment with id " + appointmentId + " not found"));
        appointment.setStatus(AppointmentStatus.CANCELED);
        appointmentRepository.save(appointment);
    }

    public void completeAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment with id " + appointmentId + " not found"));
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointment);
    }

}
