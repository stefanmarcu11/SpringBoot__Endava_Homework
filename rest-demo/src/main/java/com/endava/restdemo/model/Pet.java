package com.endava.restdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    private Long id;
    private Category category;
    private String name;
    private String[] photoUrl;
    private Tag[] tags;
    private PetStatus status;
}
