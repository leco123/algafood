package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.infrastructure.repository.RestauranteRepositoryImpl;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepositoryImpl restauranteRepositoryImpl = applicationContext.getBean(RestauranteRepositoryImpl.class);

		List<Restaurante> restaurantes = restauranteRepositoryImpl.todas();

		System.out.println("\n");
		System.out.println("+-------------------- CONSULTAR TODOS RESTAURANTES ------------------------------------+");
		for (Restaurante restaurante : restaurantes) {
			System.out.printf("| Restaurante código: %s nome: %s taxa frete: R$ %s \n|    --> cozinha: %s \n", restaurante.getId(), restaurante.getNome(), restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
		}
		System.out.println("+--------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}

}