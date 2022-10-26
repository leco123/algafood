package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteInputModelDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputModelDisassembler restauranteInputModelDisassembler;

    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionMoldel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputModelDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            throw new RestauranteNaoEncontradoException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                      @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            // Busca o restaurante da atual da base dados
            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
            // pega todos os atributos do restauranteInput e compara com restaurante atual e faz um build faz o processo
            // de forma automática como se fosse uma annotattion @Builder
            restauranteInputModelDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
            return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId) {
        cadastroRestauranteService.excluir(restauranteId);
    }
/*
    @PatchMapping("/{restauranteId}")
    public RestauranteModel atualizarParcial(@PathVariable Long restauranteId,
                                        @RequestBody Map<String, Object> campos,
                                        HttpServletRequest request) {
        RestauranteModel restauranteAtual = toModel(cadastroRestauranteService.buscarOuFalhar(restauranteId);
        merge(campos, restauranteAtual, request);
        validate(restauranteAtual, "restaurante");
        return atualizar(restauranteId, restauranteAtual);
    }
*/
    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindResult);
        if(bindResult.hasErrors()) {
            throw new ValidacaoException(bindResult);
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Define regra que deseja falhar caso a propriedade seja iginorada
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            // Define regra que deseja falhar caso a propriedade não exista
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

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
                System.out.println("| Informação data/hora: " + OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
                System.out.println("| Nome propriedade: " + nomePropriedade + " Valor propriedade: " + valorPropriedade);
                System.out.println("| Nome propriedade: " + nomePropriedade + " Novo Valor propriedade: " + novoValor);
                System.out.println("+-----------------------------------------------------------------------------+");
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }

}
