package com.algaworks.algafood.api.model.input.restaurante.produto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FotoProdutoInput {

    private MultipartFile arquivo;
    private String descricao;
}
