package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {

    private Long id;
    private String descricao;

    @Setter
    @Getter
    public static class CidadeModel {

        private Long id;
        private String nome;
        private EnderecoModel endereco;

    }
}
