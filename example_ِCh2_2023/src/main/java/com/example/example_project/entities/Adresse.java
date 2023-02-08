package com.example.example_project.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data @AllArgsConstructor  @NoArgsConstructor
public class Adresse implements Serializable {
    private String rue;
    private Integer numero;
    private String codePostal;
    private String  ville;

}
