package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        Optional<FotoProduto> fotoExistente = produtoRepository
                .findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            // excluir a foto
            produtoRepository.delete(fotoExistente.get());
        }

        return produtoRepository.save(foto);
    }

}
