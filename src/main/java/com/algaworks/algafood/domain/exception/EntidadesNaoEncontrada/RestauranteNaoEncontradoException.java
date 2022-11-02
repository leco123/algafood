package com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long restauranteId) {
        this(String.format("Não existe um cadastro de restaurante com código %d ", restauranteId));
    }
}