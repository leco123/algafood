package com.algaworks.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.algaworks.algafood.di.modelo.Cliente;

@Profile("dev")
@TipoDoNotificador(value = NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmailMock implements Notificador {
	
	//private boolean caixaAlta;
	//private String hostServidorSmtp;
	/*
	public NotificadorEmail(String hostServidorSmtp) {
		this.hostServidorSmtp = hostServidorSmtp;
		System.out.println("NotificadorEmail");
	}
	*/
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		//if(this.caixaAlta) {
			//mensagem = mensagem.toUpperCase();
		//}
		System.out.printf("MOCK: Notificação seria enviada %s através do e-mail %s: usando SMTP: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
	
	/*
	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}
	*/

}
