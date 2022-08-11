package com.algaworks.algafood.jpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;

public class BuscarCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha = cadastroCozinha.buscar(1L);
		System.out.println("\n");
		System.out.println("+-------------------- BUSCA POR ID--------------------------+");
		System.out.printf("| Find cozinha código: %d nome: %s \n", cozinha.getId(), cozinha.getNome());
		System.out.println("+-----------------------------------------------------------+");
		System.out.println("\n");
	}
	
}