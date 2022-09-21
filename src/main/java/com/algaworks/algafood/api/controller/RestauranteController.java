package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CrudRestauranteServide;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CrudRestauranteServide cadastroRestauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId) {
		return cadastroRestauranteService.buscarOuFalhar(restauranteId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
		return cadastroRestauranteService.salvar(restaurante);
	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		BeanUtils.copyProperties(restaurante, restauranteAtual,
					"id", "formasPagamento", "endereco", "dataCadastro", "produtos");
		return restauranteRepository.save(restauranteAtual);
	}

	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId) {
		cadastroRestauranteService.excluir(restauranteId);
	}

	@PatchMapping("/{restauranteId}")
	public Restaurante  atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
		Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		merge(campos, restauranteAtual);
		return atualizar(restauranteId, restauranteAtual);
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
