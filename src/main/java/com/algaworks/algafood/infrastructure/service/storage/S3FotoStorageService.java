package com.algaworks.algafood.infrastructure.service.storage;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.exception.StorageException;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        try {
            String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

            // Url sem precisar fazer requisição na amazon, só retorna url do arquivo
            URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);

            return  FotoRecuperada
                    .builder()
                        .url(url.toString())
                    .build();
        } catch (Exception e) {
           throw new StorageException("Não foi possível recuperar o arquivo.",e);
        }
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            // Preparando Payload para fazer a chamada na API da AMAZON
            String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(novaFoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo,
                    novaFoto.getInputStream(),
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            // FAZENDO CHAMADA
            amazonS3.putObject(putObjectRequest);
        } catch (Exception e ){
            throw new StorageException("Não foi possível enviar arquivo para Amazon S3.", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

            var deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(), caminhoArquivo);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
        }
    }

    private String getCaminhoArquivo(String nomeArquivo) {
        return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
    }
}
