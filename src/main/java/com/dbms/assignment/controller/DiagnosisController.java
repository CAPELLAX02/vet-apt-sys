package com.dbms.assignment.controller;

import com.dbms.assignment.model.Diagnosis;
import com.dbms.assignment.service.DiagnosisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping("/{appointmentId}")
    public ResponseEntity<Diagnosis> addDiagnosis(@PathVariable Long appointmentId,
                                                  @RequestBody Diagnosis diagnosis) {
        return ResponseEntity.ok(diagnosisService.addDiagnosis(appointmentId, diagnosis));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<Set<Diagnosis>> getDiagnosisByPet(@PathVariable Long petId) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisByPet(petId));
    }

}
