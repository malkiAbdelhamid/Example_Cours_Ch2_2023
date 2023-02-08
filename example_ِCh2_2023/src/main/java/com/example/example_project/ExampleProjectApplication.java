package com.example.example_project;

import com.example.example_project.entities.Adresse;
import com.example.example_project.entities.Civility;
import com.example.example_project.entities.Client;
import com.example.example_project.entities.ClientDetail;
import com.example.example_project.repository.ClientDetailRepository;
import com.example.example_project.repository.ClientRepository;
import com.example.example_project.repository.CompteRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class ExampleProjectApplication implements CommandLineRunner {

    @Autowired
    ClientRepository clientRepository;

    @Resource
    CompteRepository compteRepository;

    @Resource
    ClientDetailRepository clientDetailRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExampleProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create Two Clients with different values
        clientRepository.save(
                new Client(null, "malki", Civility.Homme,"a.malki@esi-sba.dz",30,
                        Date.valueOf("1989-04-20"),null,null,null));
        clientRepository.save(
                new Client(null, "Ali", Civility.Homme,"a.ali@esi-sba.dz",20,
                        Date.valueOf("1999-05-26"),null,null,null));


        // Get the First Client (i.e., idClient=1L)
        Client c1= clientRepository.findById(1L).get();
        // And then set its name and add him an address
        c1.setNom("Abdelhamid");
        c1.setAdresse(new Adresse("adda",52,"22000","sidi bel abbes"));
        // Finally, commit the changes by invoking the Save() method
        clientRepository.save(c1);

        //Add a new Record "ClientDetailt that will be associated to the first Client "c1"
        clientDetailRepository.save(new ClientDetail(null,
                "Software Developer",c1));


        // Invoke the "findAll()" method and then print all the clients
        clientRepository.findAll().forEach(System.out::println);
    }
}
