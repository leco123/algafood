package com.algaworks.algafood.core.validation.annotation;

import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricaoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ValorZeroIncluiDescricaoValidator.class})
public @interface ValorZeroIncluirDescricao {

    String message() default "Descrição obrigatória inválida"; // pega a descrição da propriedade definida messages.properties

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String valorField();
    String descricaoField();
    String descricaoObrigatoria();

}
