package com.algaworks.algafood.api.controller.restaurante;

import com.algaworks.algafood.api.assembler.restaurante.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.restaurante.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.input.restaurante.RestauranteInput;
import com.algaworks.algafood.api.model.restaurante.RestauranteModel;
import com.algaworks.algafood.api.model.view.RestauranteView;
import com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(maxAge = 10)
@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @JsonView({RestauranteView.Resumo.class})
    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @JsonView({RestauranteView.ApenasNome.class})
    @GetMapping(params = "projecao=apenas-nome")
    public List<RestauranteModel> listarApenasNome() {
        return listar();
    }

    @JsonView({RestauranteView.Resumo.class})
    @GetMapping(params = "projecao=resumo")
    public List<RestauranteModel> listarResumo() {
        return listar();
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                      @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        cadastroRestaurante.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        cadastroRestaurante.inativar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try{
            cadastroRestaurante.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try{
            cadastroRestaurante.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        cadastroRestaurante.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        cadastroRestaurante.fechar(restauranteId);
    }
}