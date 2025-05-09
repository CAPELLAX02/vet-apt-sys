package com.dbms.assignment.service;

import com.dbms.assignment.dto.SpecializationRequest;
import com.dbms.assignment.dto.SpecializationResponse;
import com.dbms.assignment.model.Specialization;
import com.dbms.assignment.model.User;
import com.dbms.assignment.repository.SpecializationRepository;
import com.dbms.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final UserRepository userRepository;

    public SpecializationService(SpecializationRepository specializationRepository,
                                 UserRepository userRepository)
    {
        this.specializationRepository = specializationRepository;
        this.userRepository = userRepository;
    }

    public SpecializationResponse addSpecialization(SpecializationRequest request) {
        User vet = userRepository.findById(request.vetId())
                .orElseThrow(() -> new RuntimeException("Vet not found."));

        boolean exists = specializationRepository.existsByVetAndTitle(vet, request.title());
        if (exists) {
            throw new RuntimeException("Already assigned a specialization.");
        }

        Specialization specialization = new Specialization();
        specialization.setVet(vet);
        specialization.setTitle(request.title());

        Specialization saved = specializationRepository.save(specialization);

        return new SpecializationResponse(saved.getId(), vet.getId(), vet.getName(), saved.getTitle());
    }

    public Set<SpecializationResponse> getSpecializationsForVet(Long vetId) {
        User vet = userRepository.findById(vetId)
                .orElseThrow(() -> new RuntimeException("Vet not found."));

        return specializationRepository.findAllByVet(vet).stream()
                .map(spec -> new SpecializationResponse(
                        spec.getId(),
                        vet.getId(),
                        vet.getName(),
                        spec.getTitle()
                ))
                .collect(Collectors.toSet());
    }

    public void deleteSpecialization(Long id) {
        specializationRepository.deleteById(id);
    }

    public Set<SpecializationResponse> getAllSpecializations() {
        return specializationRepository.findAll().stream()
                .map(spec -> new SpecializationResponse(
                        spec.getId(),
                        spec.getVet().getId(),
                        spec.getVet().getName(),
                        spec.getTitle()
                )).collect(Collectors.toSet());
    }

}
