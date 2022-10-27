package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        //		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
        //			.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

        var enderecoToenderecoModelTypeMapper = modelMapper().createTypeMap(
                Endereco.class, EnderecoModel.class);

        enderecoToenderecoModelTypeMapper.<String>addMapping(
                enderecoSrc->enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDestino, value) ->enderecoModelDestino.getCidade().setEstado(value));

        return modelMapper;
    }
}
