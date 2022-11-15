package com.algaworks.algafood.api.assembler.restaurante.produto;

import com.algaworks.algafood.api.model.restaurante.produto.FotoprodutoModel;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoprodutoModel toModel(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoprodutoModel.class);
    }

    /* este não precisa porque não temos coleção de fotos estamos trabalhando apenas com uma foto
    public List<FotoprodutoModel> toCollectionModel(List<FotoProduto> fotosProduto) {
        return fotosProduto.stream()
                .map(foto -> toModel(foto))
                .collect(Collectors.toList());
    }
    */
}
