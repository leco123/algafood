package com.algaworks.algafood.domain.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

/**
 * Quando usar pesquisa por data e hora de passar a annotation @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
 * para evitar erro de formatação de data e hora o spring não consegue reconhecer o formato string
 * ERRO: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'pedidoController': Lookup method resolution failed; nested exception is java.lang.IllegalStateException: Failed to introspect Class [com.algaworks.algafood.api.controller.pedido.PedidoController] from ClassLoader [org.springframework.boot.devtools.restart.classloader.RestartClassLoader@2643d422]
 */
@Getter
@Setter
public class PedidoFilter {

    private Long clienteId;
    private Long restauranteId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;
}
