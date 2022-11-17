package com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;

public class FotoProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public FotoProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de foto de produto com código %d para o restaurante de código %d",
                produtoId, restauranteId));
    }

}
