package com.endava.restdemo.service;

import com.endava.restdemo.model.Pet;
import com.endava.restdemo.model.PetStatus;
import com.endava.restdemo.model.SearchCriteria;
import com.endava.restdemo.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
    }

    public Optional<Pet> findByStatus(PetStatus findByStatus){
        return petRepository.findByStatus(findByStatus);
    }

    public void save (Pet pet) {

        petRepository.save(pet);
    }

    public List<Pet> filterBySearchCriteria(SearchCriteria criteria) {

        return new ArrayList<>();
    }

    public void delete(Long id) {

        petRepository.delete(id);
    }

    public void updatePet(Pet pet) {
        petRepository.updatePet(pet);
    }



}
