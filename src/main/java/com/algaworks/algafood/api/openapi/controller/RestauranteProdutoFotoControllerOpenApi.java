package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.restaurante.produto.FotoProdutoInput;
import com.algaworks.algafood.api.model.restaurante.produto.FotoprodutoModel;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

    @ApiOperation("Atualiza a foto do produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto atualizada"),
            @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
    })
    FotoprodutoModel atualizarFoto(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
            Long restauranteId,

            @ApiParam(value = "ID do produto", example = "1", required = true)
            Long produtoId,

            FotoProdutoInput fotoProdutoInput) throws IOException;

    @ApiOperation("Exclui a foto do produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Foto do produto excluída"),
            @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
    })
    void excluir(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
            Long restauranteId,

            @ApiParam(value = "ID do produto", example = "1", required = true)
            Long produtoId);

    @ApiOperation(value = "Busca a foto do produto de um restaurante",
            produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
    })
    FotoprodutoModel buscar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
            Long restauranteId,

            @ApiParam(value = "ID do produto", example = "1", required = true)
            Long produtoId);

    @ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
    ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
            throws HttpMediaTypeNotAcceptableException;
}
