package com.algaworks.awpag.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientIdInputDTO {
    @NotNull
    private Long id;
}
