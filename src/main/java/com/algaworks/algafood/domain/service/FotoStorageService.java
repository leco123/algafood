package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    FotoRecuperada recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default String gerarNomeArquivo(String nomeOriginal) {
        return UUID.randomUUID().toString()+"_"+nomeOriginal;
    }

    default void armazenarOuSubstituir(String nomeArquivoAntigo, NovaFoto novaFoto){
        this.armazenar(novaFoto);

        if (nomeArquivoAntigo != null) {
            this.remover(nomeArquivoAntigo);
        }
    }

    @Builder
    @Getter
    class NovaFoto {

        private String nomeArquivo;
        private String contentType;
        // Fluxo de entrada do arquivo, ou seja, possibilita a leitura do arquivo que acabou de ser feito upload;
        private InputStream inputStream;

    }

    @Builder
    @Getter
    class FotoRecuperada {

        private InputStream inputStream;
        private String url;

        public boolean temUrl() {
            return url != null;
        }

        public boolean temInputStream() {
            return inputStream != inputStream;
        }

    }
}
