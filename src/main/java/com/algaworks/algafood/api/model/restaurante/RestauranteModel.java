package com.algaworks.algafood.api.model.restaurante;

import com.algaworks.algafood.api.model.cozinha.CozinhaModel;
import com.algaworks.algafood.api.model.endereco.cidade.CidadeResumoModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private CidadeResumoModel cidade;
}
