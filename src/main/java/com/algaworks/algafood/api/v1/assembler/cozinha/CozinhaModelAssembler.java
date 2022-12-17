package com.algaworks.algafood.api.v1.assembler.cozinha;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.model.cozinha.CozinhaModel;
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

    @Autowired
    private AlgaLinks algaLinks;

    public CozinhaModelAssembler() {
        super(CozinhaController.class, CozinhaModel.class);
    }

    public CozinhaModel toModel(Cozinha cozinha) {
        // adiciona 1º link com a cozinha "_self" id exemplo: http://.../cozinhas/1
        CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);

        modelMapper.map(cozinha, CozinhaModel.class);

        cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));
        // adiciona 2º link com todas as cozinhas exemplo: http://.../cozinhas
        cozinhaModel.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel("cozinhas"));

        return cozinhaModel;
    }

}