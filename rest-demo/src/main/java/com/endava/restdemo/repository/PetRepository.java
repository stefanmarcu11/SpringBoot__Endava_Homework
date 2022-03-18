package com.endava.restdemo.repository;

import com.endava.restdemo.model.Category;
import com.endava.restdemo.model.Pet;
import com.endava.restdemo.model.PetStatus;
import com.endava.restdemo.model.Tag;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class PetRepository {
    private Map<Long, Pet> pets = new HashMap<>();

    @PostConstruct
    private void init() {
        createPets();
    }

    private void createPets() {
        Category cat1 = new Category(1L, "Category 1");
        Category cat2 = new Category(2L, "Category 2");
        Category cat3 = new Category(3L, "Category 3");

        Tag tag1 = new Tag(1L, "Tag 1");
        Tag tag2 = new Tag(2L, "Tag 2");
        Tag tag3 = new Tag(3L, "Tag 3");

        pets.put(1L,
            new Pet(1L, cat1, "Pet1", new String[] {}, new Tag[] {tag1}, PetStatus.AVAILABLE )
        );

        pets.put(2L,
                new Pet(2L, cat2, "Pet2", new String[] {}, new Tag[] {tag1, tag2}, PetStatus.PENDING )
        );

        pets.put(3L,
                new Pet(3L, cat3, "Pet3", new String[] {}, new Tag[] {tag3}, PetStatus.SOLD )
        );
    }

    public List<Pet> findAll () {
        return new ArrayList<>(pets.values());
    }

    public void save(Pet pet) {
        pets.compute(pet.getId(), (key, value) -> pet);
    }

    public Optional<Pet> findById(Long id) {
        return Optional.ofNullable(pets.getOrDefault(id, null));
    }

    public Optional<Pet> findByStatus(PetStatus status){
        return Optional.ofNullable(pets.getOrDefault(status,null));
    }

    public void delete(Long id) {
        pets.remove(id);
    }

    public void updatePet(Pet pet) {
        pets.put(pet.getId(), pet);
    }

}
