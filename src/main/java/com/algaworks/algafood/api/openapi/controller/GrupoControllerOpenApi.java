package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.usuario.grupo.GrupoInput;
import com.algaworks.algafood.api.model.usuario.grupo.GrupoModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation("Lista os grupos")
    CollectionModel<GrupoModel> listar();

    @ApiOperation("Buscar um grupo por ID")
    @ApiResponses({
            @ApiResponse(code=400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code=404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoModel buscar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Cadastrar um grupo")
    @ApiResponses({@ApiResponse(code=401, message = "Grupo cadastrado")})
    GrupoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um nobo grupo", required = true) GrupoInput grupoInput);

    @ApiOperation("Atualizar um grupo por ID")
    @ApiResponses({
            @ApiResponse(code=200, message = "Grupo atualizado"),
            @ApiResponse(code=404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoModel atualizar(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
            Long grupoId,
            @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados")
            GrupoInput grupoInput);

    @ApiOperation("Excluir um grupo por ID")
    @ApiResponses({
            @ApiResponse(code=204, message = "Grupo excluído"),
            @ApiResponse(code=404, message = "Grupo não encontrado", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);
}
