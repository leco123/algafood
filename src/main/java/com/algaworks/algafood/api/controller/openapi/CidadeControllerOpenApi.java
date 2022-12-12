package com.algaworks.algafood.api.controller.openapi;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.endereco.cidade.CidadeModel;
import com.algaworks.algafood.api.model.input.endereco.cidade.CidadeInput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    public List<CidadeModel> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code=400, message = "ID da cidade inválido", response = Problem.class),
            @ApiResponse(code=404, message = "Cidade não encontrada", response = Problem.class)
    })
    public CidadeModel buscar(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses({@ApiResponse(code=401, message = "Cidade cadastrada")})
    public CidadeModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cidade") CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code=200, message = "Cidade atualizada"),
            @ApiResponse(code=404, message = "Cidade não encontrada", response = Problem.class)
    })
    public CidadeModel atualizar(
            @ApiParam(value = "ID de uma cidade", example = "1")
            Long cidadeId,
            @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
            CidadeInput cidadeInput);

    @ApiOperation("Exclui uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code=204, message = "Cidade excluída"),
            @ApiResponse(code=404, message = "Cidade não encontrada", response = Problem.class)
    })
    public void remover(
            @ApiParam(value = "ID de uma cidade", example = "1")
            Long cidadeId);

}
