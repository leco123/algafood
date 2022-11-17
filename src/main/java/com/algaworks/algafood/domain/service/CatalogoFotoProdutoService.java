package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoProduto> fotoExistente = produtoRepository
                .findFotoById(restauranteId, produtoId);

        // excluir a foto
        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeNovoArquivo);
        // Primeiro deve tentar salvar no banco de dados e caso de algum problema não precisa ser feitorolback
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto =  FotoStorageService.NovaFoto
                .builder()
                    .nomeArquivo(foto.getNomeArquivo())
                    .inputStream(dadosArquivo)
                .build();

        fotoStorage.armazenarOuSubstituir(nomeArquivoExistente, novaFoto);

        return foto;
    }

}
