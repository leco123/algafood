package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.model.mixin.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Grupo.class, GrupoMixin.class);
        setMixInAnnotation(Pedido.class, PedidoMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}
