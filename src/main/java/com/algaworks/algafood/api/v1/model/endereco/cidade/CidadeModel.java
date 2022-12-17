package com.algaworks.algafood.api.v1.model.endereco.cidade;

import com.algaworks.algafood.api.v1.model.endereco.estado.EstadoModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
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