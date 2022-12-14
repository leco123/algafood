package com.algaworks.algafood.api.v1.model.endereco;

import com.algaworks.algafood.api.v1.model.endereco.cidade.CidadeResumoModel;
import com.algaworks.algafood.api.v1.model.endereco.estado.EstadoModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel extends EstadoModel {

    @ApiModelProperty(example = "38400-000")
    private String cep;

    @ApiModelProperty(example = "Rua Floriano Peixoto")
    private String logradouro;

    @ApiModelProperty(example = "\"1500\"")
    private String numero;

    @ApiModelProperty(example = "Apto 901")
    private String complemento;

    @ApiModelProperty(example = "Centro")
    private String bairro;

    private CidadeResumoModel cidade;
}
