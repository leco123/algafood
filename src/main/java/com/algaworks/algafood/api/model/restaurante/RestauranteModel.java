package com.algaworks.algafood.api.model.restaurante;

import com.algaworks.algafood.api.model.cozinha.CozinhaModel;
import com.algaworks.algafood.api.model.endereco.cidade.CidadeResumoModel;
import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {

    @JsonView({RestauranteView.Resumo.class,RestauranteView.ApenasNome.class})
    private Long id;

    @JsonView({RestauranteView.Resumo.class,RestauranteView.ApenasNome.class})
    private String nome;

    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaModel cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private CidadeResumoModel cidade;
}
