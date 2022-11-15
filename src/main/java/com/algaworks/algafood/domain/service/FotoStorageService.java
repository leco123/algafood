package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    @Builder
    @Getter
    class NovaFoto {
        private String nomeArquivo;
        // Fluxo de entrada do arquivo, ou seja, possibilita a leitura do arquivo que acabou de ser feito upload;
        private InputStream inputStream;
    }
}
