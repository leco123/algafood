package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoModelAssembler {
        @Autowired
        private ModelMapper modelMapper;

        public PedidoModel toModel(Pedido pedido) {
            return modelMapper.map(pedido, PedidoModel.class);
        }

        public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
            return pedidos.stream()
                    .map(pedido -> toModel(pedido))
                    .collect(Collectors.toList());
        }
}