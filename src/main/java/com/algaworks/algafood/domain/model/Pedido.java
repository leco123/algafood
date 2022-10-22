package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.core.validation.annotation.TaxaFrete;
import com.algaworks.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @TaxaFrete
    @Column(nullable = false, name = "taxa_frete")
    private BigDecimal taxaFrete;

    @Column(nullable = false, name = "valor_total")
    private BigDecimal valorTotal;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    @Column(name = "data_cancelamento")
    private LocalDateTime  dataCancelamento;

    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    @JsonIgnore
    @Embedded
    private Endereco enderecoEntrega;

    @Column(length = 10, name = "status", nullable = false)
    private StatusPedido statusPedido;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id",nullable = false)
    private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

}
