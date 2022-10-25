package com.algaworks.algafood.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CozinhaModel {
    private Long id;
    private String nome;
}
