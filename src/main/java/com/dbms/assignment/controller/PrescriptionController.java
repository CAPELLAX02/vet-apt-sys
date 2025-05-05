package com.dbms.assignment.controller;

import com.dbms.assignment.model.Prescription;
import com.dbms.assignment.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/diagnosis/{diagnosisId}")
    public ResponseEntity<Prescription> addPrescription(@PathVariable Long diagnosisId,
                                                        @RequestBody Prescription prescription) {
        return ResponseEntity.ok(prescriptionService.addPrescription(diagnosisId, prescription));
    }

    @GetMapping("/diagnosis/{diagnosisId}")
    public ResponseEntity<Set<Prescription>> getPrescriptionsByDiagnosisId(@PathVariable Long diagnosisId) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByDiagnosisId(diagnosisId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrescriptionById(@PathVariable Long id) {
        prescriptionService.deletePrescriptionById(id);
        return ResponseEntity.ok("Prescription deleted successfully");
    }

}
