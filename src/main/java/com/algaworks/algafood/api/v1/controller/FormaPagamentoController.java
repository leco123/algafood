package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.restaurante.formas_pagamento.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.restaurante.formas_pagamento.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.v1.model.input.restaurante.formas_pagamento.FormaPagamentoInput;
import com.algaworks.algafood.api.v1.model.restaurante.formas_pagamento.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/v1/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController  implements FormaPagamentoControllerOpenApi {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

	@CheckSecurity.FormasPagamento.PodeConsultar
	@GetMapping
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request) {
	//public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request) {
		/*
		 * Exemplo de uso de Otimiza????o de DeepETag
		 * USAR O DeepEtag s?? quando precisar fazer uma otimiza????o complexa

		// Desabilitar as configura????es do ContentCaching passado na classe WebConfig metodo shallowEtagHeaderFilter
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		// 0 = Representa que n??o existe a ??ltima data de atualiza????o na base de dados
		String eTag ="0";

		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

		// Gerando hash do Etag
		if (dataUltimaAtualizacao != null) {
			// toEpochSecond, retorna o n??mero de segundos desde 1970 at?? data atual
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}

		// valida o cabe??alho da requisi????o conhecide e retorna null, para n??o precisar fazer todo o processo de busca,
		// coverter cole????o e executar ResposeEntity
		if (request.checkNotModified(eTag)) {
			return null;
		}
		*/

		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();

		CollectionModel<FormaPagamentoModel> formasPagamentosModel =
				formaPagamentoModelAssembler.toCollectionModel(todasFormasPagamentos);

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
				//.eTag(eTag)
				.body(formasPagamentosModel);
	}

	@CheckSecurity.FormasPagamento.PodeConsultar
	// Com implementa????o de otimiza????o do DeepETag, forma expl??cita
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId,
													  ServletWebRequest request) {

		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		String eTag = "0";

		OffsetDateTime dataAtualizacao = formaPagamentoRepository
				.getDataAtualizacaoById(formaPagamentoId);

		if (dataAtualizacao != null) {
			eTag = String.valueOf(dataAtualizacao.toEpochSecond());
		}

		if (request.checkNotModified(eTag)) {
			return null;
		}

		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

		FormaPagamentoModel formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formaPagamentoModel);
	}


	/* Sem implementa????o de otimiza????o do DeepETag, forma normal
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

		FormaPagamentoModel formaPagamentoModel =  formaPagamentoModelAssembler.toModel(formaPagamento);

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
				//.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
				//.cacheControl(CacheControl.noCache())
				//.cacheControl(CacheControl.noStore())
				.body(formaPagamentoModel);
	}
	*/

	@CheckSecurity.FormasPagamento.PodeConsultar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

		formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);

		return formaPagamentoModelAssembler.toModel(formaPagamento);
	}

	@CheckSecurity.FormasPagamento.PodeEditar
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
										 @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

		formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

		formaPagamentoAtual = cadastroFormaPagamento.salvar(formaPagamentoAtual);

		return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
	}
	@CheckSecurity.FormasPagamento.PodeEditar

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		cadastroFormaPagamento.excluir(formaPagamentoId);
	}
}