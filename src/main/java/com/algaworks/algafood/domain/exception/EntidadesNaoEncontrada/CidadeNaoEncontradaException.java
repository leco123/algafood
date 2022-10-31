package com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long cidadeId) {
        this(String.format("Não existe um cadastro de cidade com código %d ", cidadeId));
    }
}