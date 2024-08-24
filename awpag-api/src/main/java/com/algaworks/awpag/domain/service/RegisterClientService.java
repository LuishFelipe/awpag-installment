package com.algaworks.awpag.domain.service;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Client;
import com.algaworks.awpag.domain.repository.IClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegisterClientService {

    private final IClientRepository clientRepository;

    @Transactional
    public Client save(Client client){
        boolean emailUsed = clientRepository.findByEmail(client.getEmail())
                .filter(c -> !c.equals(client))
                .isPresent();

        if(emailUsed){
            throw new NegocioException("Already exists an email register!");
        }

        return clientRepository.save(client);
    }


    public Client search(Long clientId){
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new NegocioException("Client dont exists!"));

    }

    @Transactional
    public void delete(Long clientId){
        clientRepository.deleteById(clientId);
    }
}
