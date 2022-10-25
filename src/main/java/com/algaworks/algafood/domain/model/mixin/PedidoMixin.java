package com.algaworks.algafood.domain.model.mixin;

import com.algaworks.algafood.domain.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public abstract class PedidoMixin {

    @JsonIgnore
    private LocalDateTime dataCriacao;

    @JsonIgnore
    private Endereco enderecoEntrega;
}
