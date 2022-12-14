package com.algaworks.algafood.api.v1.model.input.usuario.permissao;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PermissaoInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
}
