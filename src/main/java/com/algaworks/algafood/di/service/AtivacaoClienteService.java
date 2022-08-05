package com.algaworks.algafood.di.service;                                                                                               

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.algaworks.algafood.di.modelo.Cliente;

@Component
public class AtivacaoClienteService {
	
	// Spring possui uma classe que permite disparar evento
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		// trata-se de um observable, avisando que o cliente foi ativado
		// e quem tiver escutando será avisado
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}
}
