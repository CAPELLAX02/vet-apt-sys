package com.dbms.assignment.controller;

import com.dbms.assignment.dto.CreatePetRequest;
import com.dbms.assignment.dto.PetResponseResponse;
import com.dbms.assignment.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetResponseResponse> createPet(@RequestBody CreatePetRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.createPet(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseResponse> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<Set<PetResponseResponse>> getPetsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(petService.getPetsByOwnerId(ownerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.ok("Pet deleted successfully");
    }

}
