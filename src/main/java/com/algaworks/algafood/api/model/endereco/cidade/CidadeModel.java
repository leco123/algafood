package com.algaworks.algafood.api.model.endereco.cidade;

import com.algaworks.algafood.api.model.endereco.estado.EstadoModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Setter
@Getter
public class CidadeModel extends RepresentationModel<CidadeModel> {

    //@ApiModelProperty(value = "ID da cidade", example = "1")
    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Pato Branco")
    private String nome;
    private EstadoModel estado;

}