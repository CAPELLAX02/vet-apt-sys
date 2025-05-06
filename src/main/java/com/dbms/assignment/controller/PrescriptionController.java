package com.dbms.assignment.controller;

import com.dbms.assignment.dto.PrescriptionRequest;
import com.dbms.assignment.dto.PrescriptionResponse;
import com.dbms.assignment.service.PrescriptionService;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/add")
    public ResponseEntity<PrescriptionResponse> addPrescription(@RequestBody PrescriptionRequest req) {
        return ResponseEntity.ok(prescriptionService.addPrescription(req));
    }

    @GetMapping("/diagnosis/{diagnosisId}")
    public ResponseEntity<Set<PrescriptionResponse>> getPrescriptionsByDiagnosisId(@PathVariable Long diagnosisId) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByDiagnosisId(diagnosisId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionResponse> getPrescriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrescriptionById(@PathVariable Long id) {
        prescriptionService.deletePrescriptionById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Prescription has been deleted.");
    }

}
