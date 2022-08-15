package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRepository.todas();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml(){
		return new CozinhasXmlWrapper(cozinhaRepository.todas());
	}

	@GetMapping("/{cozinhaid}")
	// ResponseEntity<T> Permite que nós possamos personalizar nossa resposta http 
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaid") Long cozinhaid) {
		Cozinha cozinha =  cozinhaRepository.porId(cozinhaid);
		if(cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.adicionar(cozinha);
	}
	
	@PutMapping("/{cozinhaid}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaid") Long id, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cozinhaRepository.porId(id);
		
		if(cozinhaAtual == null) {
			return ResponseEntity.notFound().build();
		}
		
		// Existe duas maneiras de implementar put
		// 1º Setando todos os parâmetros
		cozinhaAtual.setNome(cozinha.getNome());
		// 2º recomendado usar a class BeanUtils.copyProperties
		// faz a cópia das propriedades cozinha e adicionar no cozinhaAtual.
		// o terceiro parametro "id" é para ignorar porque ele vem sempre nullo
		BeanUtils.copyProperties(cozinha, cozinhaAtual,"id");
		cozinhaRepository.adicionar(cozinhaAtual);
		return ResponseEntity.ok(cozinhaAtual);
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
		try {
			Cozinha cozinha = cozinhaRepository.porId(cozinhaId);
			
			if (cozinha != null) {
				cozinhaRepository.remover(cozinha);
				return ResponseEntity.noContent().build();
			}
			
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
