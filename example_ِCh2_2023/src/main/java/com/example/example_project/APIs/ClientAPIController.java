package com.example.example_project.APIs;


import com.example.example_project.entities.Client;
import com.example.example_project.entities.Compte;
import com.example.example_project.repository.ClientRepository;
import com.example.example_project.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("client-api")
public class ClientAPIController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CompteRepository compteRepository;

    @GetMapping("/client/all") //GET http://localhost:8081/client-api/client/all
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/client/{id}") //GET http://localhost:8081/client-api/client/1
    public Client getClientById(@PathVariable("id") Long idclient) {

        return clientRepository.findById(idclient).get();
    }

    //the same exemple but here we use the ResponseEntity type
    @GetMapping("/client2/{id}") //GET http://localhost:8081/client-api/client2/1
    public ResponseEntity<?> getClientById2(@PathVariable("id") Long idclient) {
        Client client=clientRepository.findById(idclient).get();
        if(client==null)
        {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(client);
    }
    @GetMapping("/client")  //GET http://localhost:8081/client-api/client?id=1
    public Client getClientById3(@RequestParam("id") Long idclient) {
        return clientRepository.findById(idclient).get();
    }

    @PostMapping("/client")  //POST http://localhost:8081/client-api/client
    public Client createNewClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PostMapping("/compte")  //POST  http://localhost:8081/client-api/compte
    public Compte createNewCopte(@RequestBody Map<String, Object> payload) {
        Compte e = new Compte();
        e.setLogin(payload.get("login").toString());
        e.setPassword(payload.get("password").toString());
        e.setDateCreation(new Date());

        Long idClient = Long.valueOf(payload.get("idClient").toString());
        e.setClient(clientRepository.findById(idClient).get());

        return compteRepository.save(e);
    }

    @PutMapping("/client/{id}")  //Put http://localhost:8081/client-api/client/1
    public Client updateClient(@PathVariable(value = "id") Long idClient,
                               @RequestBody Client client) {
        if (clientRepository.findById(idClient).isPresent()) {
            client.setIdClient(idClient);
            return clientRepository.save(client);
        }
        return null;
    }

    @DeleteMapping("/client/{id}")   //Delete http://localhost:8081/client-api/client/1

    public String DeleteClient(@PathVariable(value = "id") Long idClient){

    if (clientRepository.findById(idClient).isPresent()){
       clientRepository.deleteById(idClient);
       return "Correctly deleted";}
    return "the ID is not valid";
    }

}
