package com.algaworks.algafood.api.model.usuario;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModel {

    private Long id;
    private String nome;
    private String email;
}