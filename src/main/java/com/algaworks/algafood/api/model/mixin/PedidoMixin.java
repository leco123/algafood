package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.OffsetDateTime;

public abstract class PedidoMixin {

    @JsonIgnore
    private OffsetDateTime dataCriacao;

    @JsonIgnore
    private Endereco enderecoEntrega;
}
