package com.algaworks.algafood.api.controller.restaurante;

import com.algaworks.algafood.api.assembler.restaurante.produto.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.model.input.restaurante.produto.FotoProdutoInput;
import com.algaworks.algafood.api.model.restaurante.produto.FotoprodutoModel;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProduto;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @Autowired
    private FotoStorageService fotoStorage;


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoprodutoModel atualizarFoto(@PathVariable Long restauranteId,
                                          @PathVariable Long produtoId,
                                          @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        // Neste caso não foi usado o conceito de Disassembler devido ser algo simples de manipular
        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());

        return fotoProdutoModelAssembler.toModel(fotoSalva);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoprodutoModel buscar(@PathVariable Long restauranteId,
                                   @PathVariable Long produtoId) {

        FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
        return  fotoProdutoModelAssembler.toModel(fotoProduto);
    }

    @GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<InputStreamResource> servir(@PathVariable Long restauranteId,
                                                      @PathVariable Long produtoId) {

        FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);

        InputStream inputStream = fotoStorage.recuperar(fotoProduto.getNomeArquivo());

        return  ResponseEntity.ok()
                .body(new InputStreamResource(inputStream));
    }

}
