package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

/**
 * Quando usar pesquisa por data e hora de passar a annotation @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
 * para evitar erro de formatação de data e hora o spring não consegue reconhecer o formato string
 * ERRO: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'pedidoController': Lookup method resolution failed; nested exception is java.lang.IllegalStateException: Failed to introspect Class [com.algaworks.algafood.api.v1.controller.pedido.PedidoController] from ClassLoader [org.springframework.boot.devtools.restart.classloader.RestartClassLoader@2643d422]
 */
@Getter
@Setter
public class PedidoFilter {

    @ApiModelProperty(example = "1", value = "ID do cliente para filtro da pesquisa")
    private Long clienteId;

    @ApiModelProperty(example = "1", value = "ID do restaurante para filtro da pesquisa")
    private Long restauranteId;

    @ApiModelProperty(example = "2019-10-30T00:00:00Z", value = "Data/hora de criação inicial para filtro da pesquisa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @ApiModelProperty(example = "2019-11-01T10:00:00Z",value = "Data/hora de criação final para filtro da pesquisa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;
}
