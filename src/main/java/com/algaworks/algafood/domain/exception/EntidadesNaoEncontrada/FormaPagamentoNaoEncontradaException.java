package com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public FormaPagamentoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNaoEncontradaException(Long formaPagamentoId) {
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", formaPagamentoId));
    }

}