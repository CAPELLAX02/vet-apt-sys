package com.dbms.assignment.service;

import com.dbms.assignment.model.Appointment;
import com.dbms.assignment.model.Diagnosis;
import com.dbms.assignment.model.enums.AppointmentStatus;
import com.dbms.assignment.repository.AppointmentRepository;
import com.dbms.assignment.repository.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final AppointmentRepository appointmentRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository,
                            AppointmentRepository appointmentRepository)
    {
        this.diagnosisRepository = diagnosisRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Diagnosis addDiagnosis(Long appointmentId, Diagnosis diagnosis) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment with id " + appointmentId + " not found"));

        if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
            throw new IllegalStateException("Diagnosis can only be added to completed appointments.");
        }

        diagnosis.setAppointment(appointment);
        diagnosis.setDiagnosedAt(LocalDateTime.now());
        return diagnosisRepository.save(diagnosis);
    }

    public Set<Diagnosis> getDiagnosisByPet(Long petId) {
        return diagnosisRepository.findAll()
                .stream()
                .filter(diagnosis -> diagnosis.getAppointment().getPet().getId().equals(petId))
                .collect(Collectors.toSet());
    }

}
