package com.dbms.assignment.controller;

import com.dbms.assignment.model.Pet;
import com.dbms.assignment.service.PetService;
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

    @PostMapping("/owner/{ownerId}")
    public ResponseEntity<Pet> createPet(@PathVariable Long ownerId, @RequestBody Pet pet) {
        return ResponseEntity.ok(petService.createPet(pet, ownerId));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<Set<Pet>> getPetsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(petService.getPetsByOwner(ownerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

}
