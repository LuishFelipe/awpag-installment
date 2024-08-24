package com.algaworks.awpag.api.assembler;

import com.algaworks.awpag.api.model.FinanceDTO;
import com.algaworks.awpag.api.model.input.FinanceInputDTO;
import com.algaworks.awpag.domain.model.Finance;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class FinanceAssembler {
    private final ModelMapper modelMapper;

    public FinanceDTO toModel(Finance finance){
        return modelMapper.map(finance, FinanceDTO.class);
    }

    public List<FinanceDTO> toCollectionModel(List<Finance> finances){
        return finances.stream()
                .map(this::toModel)
                .toList();
    }

    public Finance toEntity(FinanceInputDTO financeInputDTO){
        return modelMapper.map(financeInputDTO, Finance.class);
    }

}
