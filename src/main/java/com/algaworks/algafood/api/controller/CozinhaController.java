package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{cozinhaid}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaid") Long cozinhaid) {
		Optional<Cozinha> cozinha =  cozinhaRepository.findById(cozinhaid);
		if(cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.adicionar(cozinha);
	}
	
	@PutMapping("/{cozinhaid}")
	public ResponseEntity<Optional<Cozinha>> atualizar(@PathVariable("cozinhaid") Long id, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
		
		if(!cozinhaAtual.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		cozinhaAtual.get().setNome(cozinha.getNome());
		BeanUtils.copyProperties(cozinha, cozinhaAtual.get(),"id");
		cozinhaRepository.save(cozinhaAtual.get());
		return ResponseEntity.ok(cozinhaAtual);
	}
	
	/*
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
		try {
			cadastroCozinhaService.excluir(cozinhaId);
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) { // Entidade não foi encontrada retorna um 404 notfound
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado!");
		} catch (DataIntegrityViolationException e) { // Entidade esta em uso retorna 409 Em conflito
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	*/

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinhaService.excluir(cozinhaId);
	}
}
