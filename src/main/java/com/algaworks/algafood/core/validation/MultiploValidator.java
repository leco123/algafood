package com.algaworks.algafood.core.validation;

import com.algaworks.algafood.core.validation.annotation.Multiplo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

    private int numeroMultiplo;

    @Override
    public void initialize(Multiplo multiplo) {
        this.numeroMultiplo = multiplo.numero();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        boolean valido = true;
        if(value != null) {
            var valorDecimal = BigDecimal.valueOf(value.doubleValue());
            var multiplDecimal = BigDecimal.valueOf(this.numeroMultiplo);
            var resto = valorDecimal.remainder(multiplDecimal);
            valido = BigDecimal.ZERO.compareTo(resto) == 0;
        }
        return valido;
    }
}
