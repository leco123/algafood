package com.algaworks.algafood.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidadionConfig {

    /**
     * Serve para específicar que vamos usar MessageSource para validar as mensagens (messages.properties)
     * em vez de ValidationMessages.properties
     * @param mensagemSource
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource mensagemSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(mensagemSource);
        return bean;
    }

}
