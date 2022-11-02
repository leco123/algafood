package com.algaworks.algafood.api.assembler.pedido;

import com.algaworks.algafood.api.model.pedido.PedidoResumoModel;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoModelAssembler {
        @Autowired
        private ModelMapper modelMapper;

        public PedidoResumoModel toModel(Pedido pedido) {
            return modelMapper.map(pedido, PedidoResumoModel.class);
        }

        public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
            return pedidos.stream()
                    .map(pedido -> toModel(pedido))
                    .collect(Collectors.toList());
        }
}