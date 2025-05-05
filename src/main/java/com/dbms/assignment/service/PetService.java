package com.dbms.assignment.service;

import com.dbms.assignment.dto.OwnerSummaryDTO;
import com.dbms.assignment.dto.PetRequestDTO;
import com.dbms.assignment.dto.PetResponseDTO;
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

    public PetResponseDTO createPet(PetRequestDTO dto) {
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

    public PetResponseDTO getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return mapToDTO(pet);
    }

    public Set<PetResponseDTO> getPetsByOwnerId(Long ownerId) {
        return petRepository.findAll().stream()
                .filter(p -> p.getOwner().getId().equals(ownerId))
                .map(this::mapToDTO)
                .collect(Collectors.toSet());
    }

    private PetResponseDTO mapToDTO(Pet pet) {
        OwnerSummaryDTO owner = new OwnerSummaryDTO(
                pet.getOwner().getId(),
                pet.getOwner().getName(),
                pet.getOwner().getEmail(),
                pet.getOwner().getPhone()
        );

        return new PetResponseDTO(
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
        petRepository.deleteById(id);
    }

}
