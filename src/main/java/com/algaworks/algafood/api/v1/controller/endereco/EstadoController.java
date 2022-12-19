package com.algaworks.algafood.api.v1.controller.endereco;

import com.algaworks.algafood.api.v1.assembler.endereco.EstadoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.endereco.EstadoModelAssembler;
import com.algaworks.algafood.api.v1.model.endereco.estado.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.endereco.estado.EstadoInput;
import com.algaworks.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<EstadoModel> listar() {
		List<Estado> todosEstados = estadoRepository.findAll();

		return estadoModelAssembler.toCollectionModel(todosEstados);
	}

	@GetMapping(value = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstadoModel buscar(@PathVariable Long estadoId) {
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

		return estadoModelAssembler.toModel(estado);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

		estado = cadastroEstado.salvar(estado);

		return estadoModelAssembler.toModel(estado);
	}

	@PutMapping(value = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstadoModel atualizar(@PathVariable Long estadoId,
								 @RequestBody @Valid EstadoInput estadoInput) {
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

		estadoAtual = cadastroEstado.salvar(estadoAtual);

		return estadoModelAssembler.toModel(estadoAtual);
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}

}