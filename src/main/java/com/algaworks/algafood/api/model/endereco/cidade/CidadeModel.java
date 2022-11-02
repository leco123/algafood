package com.algaworks.algafood.api.model.endereco.cidade;

import com.algaworks.algafood.api.model.endereco.estado.EstadoModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;

}