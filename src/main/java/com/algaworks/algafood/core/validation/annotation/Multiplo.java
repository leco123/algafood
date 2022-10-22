package com.algaworks.algafood.core.validation.annotation;

import com.algaworks.algafood.core.validation.MultiploValidator;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { MultiploValidator.class})
public @interface Multiplo {

    int numero();

    String message() default "múltiplo inválido"; // pega a descrição da propriedade definida messages.properties

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
