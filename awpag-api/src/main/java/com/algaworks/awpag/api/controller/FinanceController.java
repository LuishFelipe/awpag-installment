package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.api.assembler.FinanceAssembler;
import com.algaworks.awpag.api.model.FinanceDTO;
import com.algaworks.awpag.api.model.input.FinanceInputDTO;
import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Finance;
import com.algaworks.awpag.domain.repository.IFinanceRepository;
import com.algaworks.awpag.domain.service.FinanceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/finances")
public class FinanceController {

    private final IFinanceRepository financeRepository;
    private final FinanceService financeService;
    private final FinanceAssembler financeAssembler;

    @GetMapping
    public List<FinanceDTO> list(){
         return financeAssembler.toCollectionModel(financeRepository.findAll());
    }

    @GetMapping("/{financeId}")
    public ResponseEntity<FinanceDTO> search(@PathVariable Long financeId){
        return financeRepository.findById(financeId)
                .map(financeAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FinanceDTO create(@Valid @RequestBody FinanceInputDTO financeInputDTO){
        Finance finance = financeAssembler.toEntity(financeInputDTO);
        Finance registerFinance = financeService.save(finance);
        return financeAssembler.toModel(registerFinance);
    }

}
