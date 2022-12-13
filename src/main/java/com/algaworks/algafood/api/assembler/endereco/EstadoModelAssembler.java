package com.algaworks.algafood.api.assembler.endereco;

import com.algaworks.algafood.api.model.endereco.EnderecoModel;
import com.algaworks.algafood.api.model.endereco.estado.EstadoModel;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EnderecoModel toModel(Estado estado) {
        return modelMapper.map(estado, EnderecoModel.class);
    }

    public List<EstadoModel> toCollectionModel(List<Estado> estados) {
        return estados.stream()
                .map(estado -> toModel(estado))
                .collect(Collectors.toList());
    }

}
