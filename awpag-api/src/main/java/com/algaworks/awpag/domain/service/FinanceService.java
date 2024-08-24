package com.algaworks.awpag.domain.service;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Client;
import com.algaworks.awpag.domain.model.Finance;
import com.algaworks.awpag.domain.repository.IClientRepository;
import com.algaworks.awpag.domain.repository.IFinanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Service
public class FinanceService {

    private final IFinanceRepository financeRepository;
    private final RegisterClientService registerClientService;

    @Transactional
    public Finance save(Finance finance){
        if(finance.getId() != null){
            throw new NegocioException("Finance to be create dont must have a code id!");
        }

        Client client = registerClientService.search(finance.getClient().getId());

        finance.setClient(client);
        finance.setDataCriacao(OffsetDateTime.now());

        return financeRepository.save(finance);

    }
}
