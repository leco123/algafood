package com.algaworks.algafood.api.v1.openapi.model;

import com.algaworks.algafood.api.v1.model.cozinha.CozinhaModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CozinhasModel")
@Setter
@Getter
public class CozinhasModelOpenApi {
    private CozinhaEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("CozinhasEmbeddedModel")
    @Data
    public class CozinhaEmbeddedModelOpenApi {
        private List<CozinhaModel> cozinhas;
    }
}
