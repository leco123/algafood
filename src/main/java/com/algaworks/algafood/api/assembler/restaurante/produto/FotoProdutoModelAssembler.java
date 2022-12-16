package com.algaworks.algafood.api.assembler.restaurante.produto;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.restaurante.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.model.restaurante.produto.FotoProdutoModel;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public FotoProdutoModelAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
    }

    @Override
    public FotoProdutoModel toModel(FotoProduto foto) {
        FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);

        fotoProdutoModel.add(algaLinks.linkToFotoProduto(
                foto.getRestauranteId(), foto.getProduto().getId()));

        fotoProdutoModel.add(algaLinks.linkToProduto(
                foto.getRestauranteId(), foto.getProduto().getId(), "produto"));

        return fotoProdutoModel;
    }

    /* este não precisa porque não temos coleção de fotos estamos trabalhando apenas com uma foto
    public List<FotoprodutoModel> toCollectionModel(List<FotoProduto> fotosProduto) {
        return fotosProduto.stream()
                .map(foto -> toModel(foto))
                .collect(Collectors.toList());
    }
    */
}
