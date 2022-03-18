package com.endava.restdemo.controller;

import com.endava.restdemo.exception.DataNotFoundException;
import com.endava.restdemo.exception.InvalidDataException;
import com.endava.restdemo.model.Pet;
import com.endava.restdemo.model.PetStatus;
import com.endava.restdemo.service.PetService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value="Pet Rest Controller", tags="/pets")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pets")
public class PetController {

    private final PetService petService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pet> getAllPet() {
        return petService.findAll();
    }

    @ApiOperation(value = "Find pet by Id ", response = Pet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not Authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!"),
            @ApiResponse(code = 404, message = "Not Found!") })
    @GetMapping(path = "/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Pet> getPetById(@PathVariable Long petId) {
        if (petId < 0) {
            throw new DataNotFoundException("pet not found");
        }
        return petService.findById(petId);
    }

    @ApiOperation(value = "Finds Pets by status ", response = Pet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation!"),
            @ApiResponse(code = 400, message = "Invalid status value!") })
    @GetMapping(path = "/{findByStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Pet> getPetByStatus(@PathVariable PetStatus findByStatus) {
        if (findByStatus==null) {
            throw new DataNotFoundException("pet not found");
        }
        return petService.findByStatus(findByStatus);

    }

    @Validated
    @ApiOperation(value = "Add a new pet to the store", response = Pet.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void savePet(@RequestBody @ApiParam @Valid Pet pet, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            throw new InvalidDataException("invalid pet");
        }

        petService.save(pet);
    }

    @Validated
    @ApiOperation(value = "Update an existing pet", response = Pet.class)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePet(@RequestBody @Valid @ApiParam Pet pet) {
        petService.updatePet(pet);
    }

    @ApiOperation(value = "Deletes a pet", response = Pet.class)
    @DeleteMapping(path = "/{petId}")
    public void deletePet(@PathVariable() Long petId)
    {

        petService.delete(petId);
    }

}
