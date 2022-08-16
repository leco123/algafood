package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudRestauranteServide {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listar(){
        return restauranteRepository.todos();
    }

    public void excluir(Long id) {
        try {
            restauranteRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw  new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurente com código %d ", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw  new EntidadeEmUsoException(
                    String.format("Restaurente de código %d não pode ser removido, pois está em uso", id)
            );
        }
    }

    public Restaurante porId(Long restaurantid) {
        try {
            return restauranteRepository.porId(restaurantid);
        } catch (EmptyResultDataAccessException e) {
            throw  new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurente com código %d ", restaurantid)
            );
        }
    }

    public Restaurante adicionar(Restaurante restaurante) {
        return restauranteRepository.adicionar(restaurante);
    }
}
