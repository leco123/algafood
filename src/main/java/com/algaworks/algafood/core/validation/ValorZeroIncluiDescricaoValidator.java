package com.algaworks.algafood.core.validation;

import com.algaworks.algafood.core.validation.annotation.ValorZeroIncluirDescricao;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluirDescricao, Object> {

    String valorField;
    String descricaoField;
    String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluirDescricao valorZeroIncluirDescricao) {
        this.valorField = valorZeroIncluirDescricao.valorField();
        this.descricaoField = valorZeroIncluirDescricao.descricaoField();
        this.descricaoObrigatoria = valorZeroIncluirDescricao.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
        boolean valido = true;
        try {
            BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField)
                    .getReadMethod()
                    .invoke(objetoValidacao);
            String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField)
                    .getReadMethod()
                    .invoke(objetoValidacao);
            if(valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null){
                valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }

            return valido;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
