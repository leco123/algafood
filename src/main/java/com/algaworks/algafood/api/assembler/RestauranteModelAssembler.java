package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.dtos.CozinhaModel;
import com.algaworks.algafood.domain.model.dtos.RestauranteModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    public List<RestauranteModel> toCollectionMoldel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

    public RestauranteModel toModel(Restaurante restaurante) {
        return RestauranteModel
                .builder()
                .id(restaurante.getId())
                .nome(restaurante.getNome())
                .taxaFrete(restaurante.getTaxaFrete())
                .cozinha(CozinhaModel
                        .builder()
                        .id(restaurante.getCozinha().getId())
                        .nome(restaurante.getCozinha().getNome())
                        .build())
                .build();
    }
}
