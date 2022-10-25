package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.mixin.RestauranteMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
