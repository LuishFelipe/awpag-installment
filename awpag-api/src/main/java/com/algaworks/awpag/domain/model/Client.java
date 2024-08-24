package com.algaworks.awpag.domain.model;


import com.algaworks.awpag.domain.validation.IValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

    @NotNull(groups = IValidationGroups.ClientId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    @Size(max = 60)
    private String nome;

    @Column
    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @Column
    @NotBlank
    @Size(max = 20)
    private String telefone;
}
