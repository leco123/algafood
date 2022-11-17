package com.algaworks.algafood.infrastructure.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;

import com.algaworks.algafood.api.model.input.restaurante.produto.FotoProdutoInput;
import com.algaworks.algafood.domain.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.algaworks.algafood.domain.service.FotoStorageService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Value("${algafood.storage.local.diretorio-fotos}")
    private Path diretorioFotos;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);
            return Files.newInputStream(arquivoPath);
        } catch (IOException e) {
            throw new StorageException("Não foi possível recuperar o arquuivo.", e);
        }
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        Path arquivoPath = getArquivoPath(nomeArquivo);
        try {
            Files.deleteIfExists(arquivoPath);
        } catch (IOException e) {
            throw new StorageException("Não foi possível excluir o arquivo", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        // Retorna caminho completo do arquivo
        // diretorioFotos = pega o valor /Users/alex/Desktop/catalogo e resolve junta com nome o path e nome do arquivo
        return diretorioFotos.resolve(Path.of(nomeArquivo));
    }
}
