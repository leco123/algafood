package com.algaworks.algafood.api.controller.endereco;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.assembler.endereco.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.endereco.CidadeModelAssembler;
import com.algaworks.algafood.api.model.endereco.cidade.CidadeModel;
import com.algaworks.algafood.api.model.input.endereco.cidade.CidadeInput;
import com.algaworks.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@ApiOperation("Lista as cidades")
	@GetMapping
	public CollectionModel<CidadeModel> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		List<CidadeModel> cidadesModel = cidadeModelAssembler.toCollectionModel(todasCidades);

		CollectionModel<CidadeModel> cidadesCollectionModel = new CollectionModel<>(cidadesModel);

		cidadesModel.forEach(cidadeModel -> {
			// http://localhost:8080/cidades/1
			// [antes como estava implementado]
			// cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class).slash(cidadeModel.getId()).withSelfRel());
			cidadeModel.add(WebMvcLinkBuilder
					.linkTo(methodOn(CidadeController.class)
							.buscar(cidadeModel.getId()))
					.withSelfRel());

			// http://localhost:8080/cidades
			// [antes como estava implementado]
			// cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withRel("cidades"));
			cidadeModel.add(WebMvcLinkBuilder
					.linkTo(methodOn(CidadeController.class)
							.listar())
					.withRel("cidades"));

			// http://localhost:8080/estados/1
			// [antes como estava implementado]
			//cidadeModel.add(WebMvcLinkBuilder.linkTo(EstadoController.class).slash(cidadeModel.getEstado().getId()).withSelfRel());
			cidadeModel.add(WebMvcLinkBuilder
					.linkTo(methodOn(EstadoController.class)
							.buscar(cidadeModel.getId()))
					.withSelfRel());
		});

		cidadesCollectionModel.add(WebMvcLinkBuilder
				.linkTo(CidadeController.class)
				.withSelfRel());

		return cidadesCollectionModel;
	}

	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(
			@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

		CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);

		/**
		 * methodOn, tem a finalidade de criar um proxy que intercepta as chamadas, entenda como:
		 *
		 * Quando é feito a chamada ao método buscar esta sendo chamado o buscar do proxy e não do CidadeController real.
		 * o proxy é como se fosse um clone da classe que estamos usando CidadeController.class, porém usado apenas
		 * para pegar os metadados vamos dizer assim.
		 *
		 * isso evita que faça/execute chamadas desnecessárias como em nosso caso precisamos apenas pegar a url isso ajuda
		 * o sistema não ficar usando ou executando recursos desnecessários, de forma resumida o proxy vai
		 * a chamada e não vai passar para a frente para CidadeController, mas vai registra um histórico... sendo assim
		 * o proxy está apenas registrando a chamada em um histórico para registra o link
		 *
		 * Veja explicação aqui: 19.9. Construindo links que apontam para métodos
		 */

		// http://localhost:8080/cidades/1
		// [antes como estava implementado]
		// cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class).slash(cidadeModel.getId()).withSelfRel());
		cidadeModel.add(WebMvcLinkBuilder
				.linkTo(methodOn(CidadeController.class)
						.buscar(cidadeModel.getId()))
				.withSelfRel());

		// http://localhost:8080/cidades
		// [antes como estava implementado]
		// cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withRel("cidades"));
		cidadeModel.add(WebMvcLinkBuilder
				.linkTo(methodOn(CidadeController.class)
						.listar())
				.withRel("cidades"));

		// http://localhost:8080/estados/1
		// [antes como estava implementado]
		//cidadeModel.add(WebMvcLinkBuilder.linkTo(EstadoController.class).slash(cidadeModel.getEstado().getId()).withSelfRel());
		cidadeModel.add(WebMvcLinkBuilder
				.linkTo(methodOn(EstadoController.class)
						.buscar(cidadeModel.getId()))
				.withSelfRel());

		return cidadeModel;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(
			@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

			cidade = cadastroCidade.salvar(cidade);

			CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);

			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());

			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(
			@PathVariable Long cidadeId,
			@RequestBody @Valid CidadeInput cidadeInput) {
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