package com.algaworks.awpag.domain.model;

import com.algaworks.awpag.domain.validation.IValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "parcelamento")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Finance {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = IValidationGroups.ClientId.class)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull
    private Client client;

    @NotBlank
    @Size(max = 20)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valorTotal;

    @NotNull
    @Positive
    @Max(12)
    private Integer quantidadeParcelas;

    private OffsetDateTime dataCriacao;
}
