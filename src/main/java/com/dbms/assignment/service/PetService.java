package com.dbms.assignment.service;

import com.dbms.assignment.dto.OwnerSummaryResponse;
import com.dbms.assignment.dto.CreatePetRequest;
import com.dbms.assignment.dto.PetResponseResponse;
import com.dbms.assignment.model.Pet;
import com.dbms.assignment.model.User;
import com.dbms.assignment.repository.PetRepository;
import com.dbms.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public PetResponseResponse createPet(CreatePetRequest dto) {
        User owner = userRepository.findById(dto.ownerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Pet pet = new Pet();
        pet.setName(dto.name());
        pet.setSpecies(dto.species());
        pet.setBreed(dto.breed());
        pet.setGender(dto.gender());
        pet.setBirthDate(dto.birthDate());
        pet.setOwner(owner);

        Pet saved = petRepository.save(pet);
        return mapToDTO(saved);
    }

    public PetResponseResponse getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return mapToDTO(pet);
    }

    public Set<PetResponseResponse> getPetsByOwnerId(Long ownerId) {
        return petRepository.findAll().stream()
                .filter(p -> p.getOwner().getId().equals(ownerId))
                .map(this::mapToDTO)
                .collect(Collectors.toSet());
    }

    private PetResponseResponse mapToDTO(Pet pet) {
        OwnerSummaryResponse owner = new OwnerSummaryResponse(
                pet.getOwner().getId(),
                pet.getOwner().getName(),
                pet.getOwner().getEmail(),
                pet.getOwner().getPhone()
        );

        return new PetResponseResponse(
                pet.getId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getBreed(),
                pet.getGender(),
                pet.getBirthDate(),
                pet.getRegisteredAt(),
                owner
        );
    }

    public void deletePetById(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pet with id " + id + " not found");
        }
    }

}
