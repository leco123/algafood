package com.algaworks.algafood.jpa;
import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		List<Cozinha> cozinhas = cadastroCozinha.listar();
		
		System.out.println("\n");
		System.out.println("+-------------------- CONSULTAR TODAS COZINHAS -------------+");
		for (Cozinha cozinha : cozinhas) {
			System.out.printf("| Cozinha código: %s nome: %s \n", cozinha.getId(), cozinha.getNome());
		}
		System.out.println("+-----------------------------------------------------------+");
		System.out.println("\n");
	}
	
}