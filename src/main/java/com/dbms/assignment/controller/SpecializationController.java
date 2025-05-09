package com.dbms.assignment.controller;

import com.dbms.assignment.dto.SpecializationRequest;
import com.dbms.assignment.dto.SpecializationResponse;
import com.dbms.assignment.service.SpecializationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/specializations")
public class SpecializationController {

    private final SpecializationService specializationService;

    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping
    public ResponseEntity<SpecializationResponse> addSpecialization(@RequestBody SpecializationRequest req) {
        return ResponseEntity.ok(specializationService.addSpecialization(req));
    }

    @GetMapping("/vet/{vetId}")
    public ResponseEntity<Set<SpecializationResponse>> getSpecializationForVet(@PathVariable Long vetId) {
        return ResponseEntity.ok(specializationService.getSpecializationsForVet(vetId));
    }

    @GetMapping
    public ResponseEntity<Set<SpecializationResponse>> getAllSpecializations() {
        return ResponseEntity.ok(specializationService.getAllSpecializations());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpecialization(@PathVariable Long id) {
        specializationService.deleteSpecialization(id);
        return ResponseEntity.ok("Deleted specialization.");
    }

}
