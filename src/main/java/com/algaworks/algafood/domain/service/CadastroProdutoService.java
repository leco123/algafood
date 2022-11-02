package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.exception.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }
}
