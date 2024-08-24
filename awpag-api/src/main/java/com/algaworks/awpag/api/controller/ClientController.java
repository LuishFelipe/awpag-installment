package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Client;
import com.algaworks.awpag.domain.repository.IClientRepository;
import com.algaworks.awpag.domain.service.RegisterClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final RegisterClientService registerClientService;
    private final IClientRepository clientRepository;

    @GetMapping
    public List<Client> list(){
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> search(@PathVariable Long clientId){
        Optional<Client> client = clientRepository.findById(clientId);

        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Client create(@Valid @RequestBody Client client){
        return registerClientService.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update(@PathVariable Long clientId,@Valid @RequestBody Client client){
        if(!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }

        client.setId(clientId);
        client = registerClientService.save(client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId){

        if(!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }

        registerClientService.delete(clientId);

        return ResponseEntity.noContent().build();
    }


}
