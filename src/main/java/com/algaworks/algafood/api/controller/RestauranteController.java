package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CrudRestauranteServide;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = crudRestauranteServide.adicionar(restaurante);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(restaurante);
		} catch (EntidadeNaoEncontradaException e) { // Entidade não foi encontrada retorna um 400 badrequest
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{restaurantid}")
	public ResponseEntity<?> atualizar(@PathVariable("restaurantid") Long id, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = crudRestauranteServide.porId(id);
			if (restauranteAtual == null) {
				return ResponseEntity.notFound().build();
			}
			restauranteAtual.setNome(restaurante.getNome());
			BeanUtils.copyProperties(restaurante, restauranteAtual,"id");
			crudRestauranteServide.adicionar(restauranteAtual);
			return ResponseEntity.ok(restauranteAtual);
		} catch (EntidadeNaoEncontradaException e) { // Entidade Cozinha não foi encontrada retorna um 404 notfound
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
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

	@PatchMapping("/{restaurantid}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restaurantid,
											  @RequestBody Map<String, Object> campos) {

		Restaurante restauranteAtual = crudRestauranteServide.porId(restaurantid);

		if (restauranteAtual == null ) {
			return ResponseEntity.notFound().build();
		}

		merge(campos, restauranteAtual);
		return atualizar(restaurantid, restauranteAtual);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		// Estamos dizendo aqui objectMapper convert pra mim dados de Origem em um objecto do tipo Restaurante
		// criando uma instância restauranteOrigem
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		dadosOrigem.forEach((String nomePropriedade, Object valorPropriedade) -> {
			// findField Retorna instância de um campo
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			// É necessário setar o field como true devido as propriedades de restaurante
			// serem declaradas como private
			field.setAccessible(true);
			// getField retorna o valor da propriedade representado por field
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			System.out.println("+------------------------- PROPRIEDADES PATH ---------------------------------+");
			System.out.println("| Informação data/hora: "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
			System.out.println("| Nome propriedade: "+nomePropriedade+ " Valor propriedade: "+valorPropriedade);
			System.out.println("| Nome propriedade: "+nomePropriedade+ " Novo Valor propriedade: "+novoValor);
			System.out.println("+-----------------------------------------------------------------------------+");
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
}
