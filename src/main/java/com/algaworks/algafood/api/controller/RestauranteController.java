package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CrudRestauranteServide;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private CrudRestauranteServide crudRestauranteServide;

	@GetMapping
	public List<Restaurante> listar() {
		return crudRestauranteServide.listar();
	}

	@GetMapping("/{restaurantid}")
	public ResponseEntity<Restaurante> buscar(@PathVariable("restaurantid") Long restaurantid) {
		try {
			Restaurante restaurante =  crudRestauranteServide.porId(restaurantid);
			return ResponseEntity.ok(restaurante);
		} catch (EntidadeNaoEncontradaException e) { // Entidade não foi encontrada retorna um 404 notfound
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante salvar(@RequestBody Restaurante restaurante) {
		return crudRestauranteServide.adicionar(restaurante);
	}

	@PutMapping("/{restaurantid}")
	public ResponseEntity<Restaurante> atualizar(@PathVariable("restaurantid") Long id, @RequestBody Restaurante restaurante) {
		Restaurante restauranteAtual = crudRestauranteServide.porId(id);

		if(restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}
		restauranteAtual.setNome(restaurante.getNome());
		BeanUtils.copyProperties(restaurante, restauranteAtual,"id");
		crudRestauranteServide.adicionar(restauranteAtual);
		return ResponseEntity.ok(restauranteAtual);
	}

	@DeleteMapping("/{restaurantid}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long restaurantid) {
		try {
			crudRestauranteServide.excluir(restaurantid);
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) { // Entidade não foi encontrada retorna um 404 notfound
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) { // Entidade esta em uso retorna 409 Em conflito
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
