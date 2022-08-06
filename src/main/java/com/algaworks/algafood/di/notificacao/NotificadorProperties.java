package com.algaworks.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Classe usada para gerênciar grupo de propriedades definidas no arquivo
 * application.properties OBS: Há annotation @ConfigurationProperties vai
 * mostrar um aviso se deseja ou não adicionar no pom.xml a configuração para
 * que seja criado um arquivo dentro do jar para ajudar a IDE a fazer
 * autocomplite do código DEPENDÊNCIA QUE SERÁ ADICIONADA NO pom.xml
 * <dependency> <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-configuration-processor</artifactId>
 * <optional>true</optional> </dependency>
 */
@Component
@ConfigurationProperties("minha.propriedade")
public class NotificadorProperties {

	private String hostServidor = "smtp.nome.email.com.br";
	private Integer portaServidor = 25;

	public String getHostServidor() {
		return hostServidor;
	}

	public void setHostServidor(String hostServidor) {
		this.hostServidor = hostServidor;
	}

	public Integer getPortaServidor() {
		return portaServidor;
	}

	public void setPortaServidor(Integer portaServidor) {
		this.portaServidor = portaServidor;
	}
}
