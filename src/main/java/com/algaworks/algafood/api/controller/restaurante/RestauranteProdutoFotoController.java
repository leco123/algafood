package com.algaworks.algafood.api.controller.restaurante;

import com.algaworks.algafood.api.model.input.restaurante.produto.FotoProdutoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                              FotoProdutoInput fotoProdutoInput) {

        var nomeArquivo  = UUID.randomUUID().toString() +"_"+fotoProdutoInput.getArquivo().getOriginalFilename();

        var arquivoFoto = Path.of("C:/Users/Administrator/Desktop/teste", nomeArquivo);

        System.out.println(fotoProdutoInput.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoInput.getArquivo().getContentType());

       try {
           fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
