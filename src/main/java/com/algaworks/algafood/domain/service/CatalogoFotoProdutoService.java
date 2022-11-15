package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto) {
        return produtoRepository.save(foto);
    }

}
