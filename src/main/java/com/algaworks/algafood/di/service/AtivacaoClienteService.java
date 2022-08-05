package com.algaworks.algafood.di.service;                                                                                               

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;

@Component
public class AtivacaoClienteService {
	
	@TipoDoNotificador(velue = NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;
	
	@PostConstruct
	public void init() {
		System.out.println("INIT");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
	
	/* exemplo de notificador usando for
	public void ativar(Cliente cliente) {
		cliente.ativar();
		for (Notificador notificador : notificadores) {
			notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
		}
	}
	*/
	
	
//	@Autowired
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//	}
//	
//	public AtivacaoClienteService(String qualquer) {
//		
//	}
	
	// RESOLVENDO PROBLEMA DE AMBIGUIDADE DE BEANS
	//@Autowired(required = false)
	//private List<Notificador> notificadores;
	//public void ativar(Cliente cliente) {
	//	cliente.ativar();
	//	for (Notificador notificador : notificadores) {
	//		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	//	}
	//}

}
