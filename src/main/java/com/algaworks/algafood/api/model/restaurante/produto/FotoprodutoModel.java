package com.algaworks.algafood.api.model.restaurante.produto;

import com.algaworks.algafood.domain.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoprodutoModel {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
