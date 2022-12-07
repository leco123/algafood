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
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

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
	public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request) {
	//public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request) {
		/*
		 * Exemplo de uso de Otimização de DeepETag
		 * USAR O DeepEtag só quando precisar fazer uma otimização complexa

		// Desabilitar as configurações do ContentCaching passado na classe WebConfig metodo shallowEtagHeaderFilter
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		// 0 = Representa que não existe a última data de atualização na base de dados
		String eTag ="0";

		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

		// Gerando hash do Etag
		if (dataUltimaAtualizacao != null) {
			// toEpochSecond, retorna o número de segundos desde 1970 até data atual
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}

		// valida o cabeçalho da requisição conhecide e retorna null, para não precisar fazer todo o processo de busca,
		// coverter coleção e executar ResposeEntity
		if (request.checkNotModified(eTag)) {
			return null;
		}
		*/

		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();

		List<FormaPagamentoModel> formaPagamentoModel =  formaPagamentoModelAssembler.toCollectionModel(todasFormasPagamentos);

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
				//.eTag(eTag)
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