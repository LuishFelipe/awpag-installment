package com.algaworks.awpag.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class FinanceDTO {
    private Long id;
    private ResumeClientDTO client;
    private String descricao;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
}
