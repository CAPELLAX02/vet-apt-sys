package com.dbms.assignment.service;

import com.dbms.assignment.model.Pet;
import com.dbms.assignment.model.User;
import com.dbms.assignment.repository.PetRepository;
import com.dbms.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetService(PetRepository petRepository,
                      UserRepository userRepository)
    {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    public Pet createPet(Pet pet, Long ownerId) {
        User owner = userRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner with id " + ownerId + " not found"));
        pet.setOwner(owner);
        return petRepository.save(pet);
    }

    public Set<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.findAll()
                .stream()
                .filter(pet -> pet.getOwner().getId().equals(ownerId))
                .collect(Collectors.toSet());
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

}
