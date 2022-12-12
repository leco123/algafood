package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "2022-12-12T17:42:47.4357898Z", value = "Data e Hora UTC", position = 3)
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "http://localhost:8080/recurso-nao-encontrado", position = 6)
    private String type;

    @ApiModelProperty(example = "Recurso não encontrado", position = 9)
    private String title;

    @ApiModelProperty(example = "Não existe um cadastro de restaurante com código 10 ", position = 12)
    private String detail;

    @ApiModelProperty(example = "Não existe um cadastro de restaurante com código 10 ", position = 15)
    private String userMessage;

    @ApiModelProperty(value = "Objetos ou campos que geraram o erro (opcional) ", position = 18)
    private List<Object> objects;

    @ApiModel("ObjetoProblema")
    @Getter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }

}
