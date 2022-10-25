package com.algaworks.algafood.domain.model.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class CozinhaIdInput {

    @NotNull
    private Long id;
}
