package com.dbms.assignment.service;

import com.dbms.assignment.dto.DiagnosisRequest;
import com.dbms.assignment.dto.DiagnosisResponse;
import com.dbms.assignment.dto.PrescriptionResponse;
import com.dbms.assignment.model.Appointment;
import com.dbms.assignment.model.Diagnosis;
import com.dbms.assignment.model.enums.AppointmentStatus;
import com.dbms.assignment.repository.AppointmentRepository;
import com.dbms.assignment.repository.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
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

    public DiagnosisResponse createDiagnosis(DiagnosisRequest request) {
        Appointment appointment = appointmentRepository.findById(request.appointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
            throw new RuntimeException("Diagnosis can only be added to completed appointments");
        }

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setAppointment(appointment);
        diagnosis.setDescription(request.description());
        diagnosis.setNotes(request.notes());
        diagnosis.setDiagnosedAt(LocalDateTime.now());

        Diagnosis saved = diagnosisRepository.save(diagnosis);
        return new DiagnosisResponse(
                saved.getId(),
                saved.getAppointment().getId(),
                saved.getDescription(),
                saved.getNotes(),
                saved.getDiagnosedAt(),
                Set.of()
        );
    }

    public DiagnosisResponse getDiagnosisById(Long id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnosis not found"));

        Set<PrescriptionResponse> prescriptions = diagnosis.getPrescriptions().stream()
                .map(prescription -> new PrescriptionResponse(
                        prescription.getId(),
                        prescription.getMedicineName(),
                        prescription.getDosage(),
                        prescription.getInstructions()
                ))
                .collect(Collectors.toSet());

        return new DiagnosisResponse(
                diagnosis.getId(),
                diagnosis.getAppointment().getId(),
                diagnosis.getDescription(),
                diagnosis.getNotes(),
                diagnosis.getDiagnosedAt(),
                prescriptions
        );
    }

    public Set<DiagnosisResponse> getDiagnosisByPet(Long petId) {
        Set<Diagnosis> diagnoses = diagnosisRepository.findAllByAppointment_Pet_Id(petId);

        return diagnoses
                .stream()
                .map(d -> {
                    Set<PrescriptionResponse> prescriptionResponseSet = d.getPrescriptions()
                            .stream()
                            .map(p -> new PrescriptionResponse(
                                    p.getId(),
                                    p.getMedicineName(),
                                    p.getDosage(),
                                    p.getInstructions()
                            ))
                            .collect(Collectors.toSet());

                    return new DiagnosisResponse(
                            d.getId(),
                            d.getAppointment().getId(),
                            d.getDescription(),
                            d.getNotes(),
                            d.getDiagnosedAt(),
                            prescriptionResponseSet
                    );
                })
                .collect(Collectors.toSet());
    }

}
