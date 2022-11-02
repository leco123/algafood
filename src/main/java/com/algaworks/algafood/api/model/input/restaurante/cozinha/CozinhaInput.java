package com.algaworks.algafood.api.model.input.restaurante.cozinha;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CozinhaInput {

    @NotBlank
    private String nome;

}
