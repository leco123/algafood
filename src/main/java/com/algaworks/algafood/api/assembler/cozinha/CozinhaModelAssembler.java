package com.algaworks.algafood.api.assembler.cozinha;

import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.model.cozinha.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public CozinhaModelAssembler(Class<?> controllerClass,
                                 Class<CozinhaModel> resourceType) {
        super(controllerClass, resourceType);
    }

    public CozinhaModel toModel(Cozinha cozinha) {
        // adiciona 1º link com a cozinha "_self" id exemplo: http://.../cozinhas/1
        CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);

        modelMapper.map(cozinha, CozinhaModel.class);

        // adiciona 2º link com todas as cozinhas exemplo: http://.../cozinhas
        cozinhaModel.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel("cozinhas"));

        return cozinhaModel;
    }
}