package com.algaworks.algafood.api.model.pedido;

import com.algaworks.algafood.api.model.restaurante.RestauranteResumoModel;
import com.algaworks.algafood.api.model.usuario.UsuarioModel;
import com.algaworks.algafood.domain.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
//13.2. Limitando os campos retornados pela API com @JsonFilter do Jackson
//@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoModel {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private OffsetDateTime dataCriacao;
    private RestauranteResumoModel restaurante;
    //private UsuarioModel cliente;
    private String nomeCliente;
}
