package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.restaurante.formas_pagamento.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.assembler.restaurante.formas_pagamento.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.restaurante.formas_pagamento.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.restaurante.formas_pagamento.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

	@GetMapping
	public ResponseEntity<List<FormaPagamentoModel>> listar() {



		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();

		List<FormaPagamentoModel> formaPagamentoModel =  formaPagamentoModelAssembler.toCollectionModel(todasFormasPagamentos);

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(formaPagamentoModel);
	}

	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

		FormaPagamentoModel formaPagamentoModel =  formaPagamentoModelAssembler.toModel(formaPagamento);

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
				//.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
				//.cacheControl(CacheControl.noCache())
				//.cacheControl(CacheControl.noStore())
				.body(formaPagamentoModel);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

		formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);

		return formaPagamentoModelAssembler.toModel(formaPagamento);
	}

	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
										 @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

		formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

		formaPagamentoAtual = cadastroFormaPagamento.salvar(formaPagamentoAtual);

		return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		cadastroFormaPagamento.excluir(formaPagamentoId);
	}
}