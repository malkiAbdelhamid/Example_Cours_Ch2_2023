package com.example.example_project.repository;

import com.example.example_project.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface CompteRepository extends JpaRepository<Compte,Long> {
}
