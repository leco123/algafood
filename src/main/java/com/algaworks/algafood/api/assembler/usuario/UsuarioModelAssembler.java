package com.algaworks.algafood.api.assembler.usuario;

import com.algaworks.algafood.api.controller.usuario.UsuarioController;
import com.algaworks.algafood.api.controller.usuario.UsuarioGrupoController;
import com.algaworks.algafood.api.model.usuario.UsuarioModel;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@Component
public class UsuarioModelAssembler
        extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModelAssembler() {
        super(UsuarioController.class, UsuarioModel.class);
    }

    @Override
    public UsuarioModel toModel(Usuario usuario) {
        UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioModel);

        usuarioModel.add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withRel("usuarios"));

        usuarioModel.add(WebMvcLinkBuilder.linkTo(methodOn(UsuarioGrupoController.class)
                .listar(usuario.getId())).withRel("grupos-usuario"));

        return usuarioModel;
    }

    @Override
    public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities)
                .add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withSelfRel());
    }
}