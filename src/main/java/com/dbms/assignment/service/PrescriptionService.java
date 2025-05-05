package com.dbms.assignment.service;

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

    public Prescription addPrescription(Long diagnosisId, Prescription prescription) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new RuntimeException("Diagnosis with id " + diagnosisId + " not found"));

        prescription.setDiagnosis(diagnosis);
        return prescriptionRepository.save(prescription);
    }

    public Set<Prescription> getPrescriptionsByDiagnosisId(Long diagnosisId) {
        return prescriptionRepository.findAll()
                .stream()
                .filter(prescription -> prescription.getDiagnosis().getId().equals(diagnosisId))
                .collect(Collectors.toSet());
    }

    public void deletePrescriptionById(Long prescriptionId) {
        prescriptionRepository.deleteById(prescriptionId);
    }

    public Prescription getPrescriptionById(Long prescriptionId) {
        return prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription with id " + prescriptionId + " not found"));
    }

}
