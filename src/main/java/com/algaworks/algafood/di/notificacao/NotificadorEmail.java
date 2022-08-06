package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Profile("production")
@TipoDoNotificador(value = NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorProperties properties;
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.print("\n");
		System.out.print(" +--PROFILE-PRODUCTION-----------------------------------------------+\n");
		System.out.print(" | ENVIADA PARA: "+cliente.getNome()+" \n");
		System.out.print(" | ATRAVÉS DO E-MAIL: "+cliente.getEmail()+" \n");
		System.out.print(" | USANDO SMTP: "+cliente.getNome()+" \n");
		System.out.print(" | MENSAGEM DE NOTIFICAÇÃO: "+mensagem+" \n");
		System.out.print(" +-------------------------------------------------------------------+\n");
		System.out.print(" +--CONFIGURAÇÕES DA PROPERTIES--------------------------------------+\n");
		System.out.print(" | HOST: "+properties.getHostServidor()+"\n");
		System.out.print(" | PORTA: "+properties.getPortaServidor()+"\n");
		System.out.print(" +-------------------------------------------------------------------+\n\n\n");
	}
}
