package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.algaworks.algafood.di.modelo.Cliente;

@Profile("prod")
@TipoDoNotificador(value = NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {
	
	// Pegando valor de uma propriedade
	@Value("${minha.propriedade-teste}")
	private String host;
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Pegando valor de uma propriedade: minha.propriedade-teste = %s \n", host);
		System.out.printf("Notificando %s através do e-mail %s: usando SMTP: %s \n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
