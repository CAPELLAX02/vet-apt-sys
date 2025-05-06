package com.dbms.assignment.controller;

import com.dbms.assignment.dto.DiagnosisRequest;
import com.dbms.assignment.dto.DiagnosisResponse;
import com.dbms.assignment.service.DiagnosisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/diagnoses")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping("/create")
    public ResponseEntity<DiagnosisResponse> createDiagnosis(@RequestBody DiagnosisRequest req) {
        return ResponseEntity.ok(diagnosisService.createDiagnosis(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosisResponse> getDiagnosisById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisById(id));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<Set<DiagnosisResponse>> getDiagnosisByPet(@PathVariable Long petId) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisByPet(petId));
    }

}
