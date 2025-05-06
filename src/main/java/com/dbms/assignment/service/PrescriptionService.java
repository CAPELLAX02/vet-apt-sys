package com.dbms.assignment.service;

import com.dbms.assignment.dto.PrescriptionRequest;
import com.dbms.assignment.dto.PrescriptionResponse;
import com.dbms.assignment.model.Diagnosis;
import com.dbms.assignment.model.Prescription;
import com.dbms.assignment.repository.DiagnosisRepository;
import com.dbms.assignment.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DiagnosisRepository diagnosisRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               DiagnosisRepository diagnosisRepository)
    {
        this.prescriptionRepository = prescriptionRepository;
        this.diagnosisRepository = diagnosisRepository;
    }

    public PrescriptionResponse addPrescription(PrescriptionRequest req) {
        Diagnosis diagnosis = diagnosisRepository.findById(req.diagnosisId())
                .orElseThrow(() -> new RuntimeException("Diagnosis not found"));

        Prescription prescription = new Prescription();
        prescription.setDiagnosis(diagnosis);
        prescription.setMedicineName(req.medicineName());
        prescription.setDosage(req.dosage());
        prescription.setInstructions(req.instructions());

        Prescription saved = prescriptionRepository.save(prescription);

        return new PrescriptionResponse(saved.getId(), saved.getMedicineName(), saved.getDosage(), saved.getInstructions());
    }

    public Set<PrescriptionResponse> getPrescriptionsByDiagnosisId(Long diagnosisId) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new RuntimeException("Diagnosis not found"));

        return diagnosis.getPrescriptions().stream()
                .map(p -> new PrescriptionResponse(
                        p.getId(),
                        p.getMedicineName(),
                        p.getDosage(),
                        p.getInstructions()
                ))
                .collect(Collectors.toSet());
    }

    public PrescriptionResponse getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription with id " + id + " is not found"));

        return new PrescriptionResponse(
                prescription.getId(),
                prescription.getMedicineName(),
                prescription.getDosage(),
                prescription.getInstructions()
        );
    }

    public void deletePrescriptionById(Long id) {
        if (prescriptionRepository.existsById(id)) {
            prescriptionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Prescription with id " + id + " is not found");
        }
    }

}
