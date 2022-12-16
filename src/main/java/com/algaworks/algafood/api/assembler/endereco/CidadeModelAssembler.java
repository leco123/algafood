package com.algaworks.algafood.api.assembler.endereco;

import com.algaworks.algafood.api.controller.endereco.CidadeController;
import com.algaworks.algafood.api.controller.endereco.EstadoController;
import com.algaworks.algafood.api.model.endereco.cidade.CidadeModel;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeModelAssembler() {
        super(CidadeController.class, CidadeModel.class);
    }

    @Override
    public CidadeModel toModel(Cidade cidade) {

        CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModel);

//      CidadeModel cidadeModel = modelMapper.map(cidade, CidadeModel.class);

//      http://localhost:8080/cidades/1
//      [antes como estava implementado]
//      cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class).slash(cidadeModel.getId()).withSelfRel());

//      cidadeModel.add(WebMvcLinkBuilder
//           .linkTo(methodOn(CidadeController.class)
//              .buscar(cidadeModel.getId()))
//           .withSelfRel());

//      http://localhost:8080/cidades
//      [antes como estava implementado]
//      cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withRel("cidades"));
        cidadeModel.add(WebMvcLinkBuilder
                .linkTo(methodOn(CidadeController.class)
                        .listar())
                .withRel("cidades"));

//      http://localhost:8080/estados/1
//      [antes como estava implementado]
//      cidadeModel.add(WebMvcLinkBuilder.linkTo(EstadoController.class).slash(cidadeModel.getEstado().getId()).withSelfRel());
        cidadeModel.add(WebMvcLinkBuilder
                .linkTo(methodOn(EstadoController.class)
                        .buscar(cidadeModel.getId()))
                .withSelfRel());

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
                .add(WebMvcLinkBuilder.linkTo(CidadeController.class)
                        .withSelfRel());
    }


//    public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
//        return cidades.stream()
//                .map(cidade -> toModel(cidade))
//                .collect(Collectors.toList());
//    }

}
