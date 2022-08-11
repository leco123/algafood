package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.infrastructure.repository.CozinhaRepositoryImpl;

public class ExclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepositoryImpl cozinhaRepositoryImpl = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Japonesa");
		
		cozinha1 = cozinhaRepositoryImpl.adicionar(cozinha1);
		cozinha2 = cozinhaRepositoryImpl.adicionar(cozinha2);
		System.out.println("\n");
		System.out.println("+-------------------- LISTA DE COZINHAS ADICIONADAS --------+");
		System.out.printf("| Incluido cozinha código: %d nome: %s \n", cozinha1.getId(), cozinha1.getNome());
		System.out.printf("| Incluido cozinha código: %d nome: %s \n", cozinha2.getId(), cozinha2.getNome());
		System.out.println("+-----------------------------------------------------------+");
		System.out.println("\n");
	}
	
}