package com.algaworks.algafood.api.v2.controller.endereco;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v2.assembler.endereco.CidadeInputDisassemblerV2;
import com.algaworks.algafood.api.v2.assembler.endereco.CidadeModelAssemblerV2;
import com.algaworks.algafood.api.v2.model.endereco.cidade.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.input.endereco.cidade.CidadeInputV2;
import com.algaworks.algafood.core.web.AlgaMediaTypes;
import com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cidades", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
public class CidadeControllerV2 {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeModelAssemblerV2 cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassemblerV2 cidadeInputDisassembler;

	@ApiOperation("Lista as cidades")
	@GetMapping
	public CollectionModel<CidadeModelV2> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		return cidadeModelAssembler.toCollectionModel(todasCidades);
	}

	@GetMapping("/{cidadeId}")
	public CidadeModelV2 buscar(
			@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModelV2 adicionar(
			@RequestBody @Valid CidadeInputV2 cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

			cidade = cadastroCidade.salvar(cidade);

			CidadeModelV2 cidadeModel = cidadeModelAssembler.toModel(cidade);

			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());

			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeModelV2 atualizar(
			@PathVariable Long cidadeId,
			@RequestBody @Valid CidadeInputV2 cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

			cidadeAtual = cadastroCidade.salvar(cidadeAtual);

			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(
			@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}

}