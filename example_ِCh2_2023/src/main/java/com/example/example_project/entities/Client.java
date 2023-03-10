package com.example.example_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="client_table")
@Data @AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column(nullable = false, length = 20)
    private String nom;

    @Enumerated(EnumType.STRING)
    private Civility sexe;

    @Column(unique = true)
    private String email;

    @Transient
    private int age;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Embedded
    private Adresse adresse;

    @ToString.Exclude
    @JsonIgnore
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private ClientDetail clientDetail;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Compte> lesComptes;

}
