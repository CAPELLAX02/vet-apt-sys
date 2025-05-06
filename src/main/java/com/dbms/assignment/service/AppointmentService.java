package com.dbms.assignment.service;

import com.dbms.assignment.dto.AppointmentResponse;
import com.dbms.assignment.dto.CreateAppointmentRequest;
import com.dbms.assignment.dto.PetSummary;
import com.dbms.assignment.dto.VetSummary;
import com.dbms.assignment.model.Appointment;
import com.dbms.assignment.model.Pet;
import com.dbms.assignment.model.User;
import com.dbms.assignment.model.enums.AppointmentStatus;
import com.dbms.assignment.repository.AppointmentRepository;
import com.dbms.assignment.repository.PetRepository;
import com.dbms.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public AppointmentResponse createAppointment(CreateAppointmentRequest request) {
        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        User vet = userRepository.findById(request.vetId())
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        if (appointmentRepository.existsByVetAndAppointmentTime(vet, request.appointmentTime())) {
            throw new RuntimeException("This vet already has an appointment at this time.");
        }

        Appointment appointment = new Appointment();
        appointment.setPet(pet);
        appointment.setVet(vet);
        appointment.setAppointmentTime(request.appointmentTime());
        appointment.setStatus(AppointmentStatus.PENDING);

        Appointment saved = appointmentRepository.save(appointment);

        return mapToDto(saved);
    }

    public Set<AppointmentResponse> getAppointmentsForVet(Long vetId) {
        return appointmentRepository.findAll().stream()
                .filter(app -> app.getVet().getId().equals(vetId))
                .map(app -> new AppointmentResponse(
                        app.getId(),
                        new PetSummary(
                                app.getPet().getId(),
                                app.getPet().getName(),
                                app.getPet().getSpecies(),
                                app.getPet().getBreed(),
                                app.getPet().getGender(),
                                app.getPet().getBirthDate()
                        ),
                        new VetSummary(
                                app.getVet().getId(),
                                app.getVet().getName(),
                                app.getVet().getEmail(),
                                app.getVet().getPhone()
                        ),
                        app.getAppointmentTime(),
                        app.getStatus().name()
                ))
                .collect(Collectors.toSet());
    }


    private AppointmentResponse mapToDto(Appointment a) {
        return new AppointmentResponse(
                a.getId(),
                new PetSummary(
                        a.getPet().getId(),
                        a.getPet().getName(),
                        a.getPet().getSpecies(),
                        a.getPet().getBreed(),
                        a.getPet().getGender(),
                        a.getPet().getBirthDate()
                ),
                new VetSummary(
                        a.getVet().getId(),
                        a.getVet().getName(),
                        a.getVet().getEmail(),
                        a.getVet().getPhone()
                ),
                a.getAppointmentTime(),
                a.getStatus().name()
        );
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

    public AppointmentResponse getAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment with id " + appointmentId + " not found"));
        return mapToDto(appointment);
    }

}
